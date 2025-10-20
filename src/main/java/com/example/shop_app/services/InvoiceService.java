package com.example.shop_app.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.invoice.InvoiceDTO;
import com.example.shop_app.DTOs.invoice.ListInvoiceDisplay;
import com.example.shop_app.DTOs.invoice.ListInvoiceMapping;
import com.example.shop_app.DTOs.product.ProductNumberDTO;
import com.example.shop_app.domains.Invoice;
import com.example.shop_app.domains.InvoiceProduct;
import com.example.shop_app.domains.Product;
import com.example.shop_app.domains.Users;
import com.example.shop_app.mapper.ICartMapper;
import com.example.shop_app.mapper.ICartProductMapper;
import com.example.shop_app.mapper.IInvoiceMapper;
import com.example.shop_app.mapper.IInvoiceProductMapper;
import com.example.shop_app.mapper.IProductMapper;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final IInvoiceMapper iInvoiceMapper;
    private final IInvoiceProductMapper iInvoiceProductMapper;
    private final IProductMapper iProductMapper;
    private final ICartProductMapper iCartProductMapper;
    private final ICartMapper iCartMapper;

    public void createInvoice(InvoiceDTO invoiceDTO) {
        //caculate total amount 
        Long totalAmount = 0L;
        for (ProductNumberDTO productNumberDTO : invoiceDTO.getListProduct()) {
            Product existProduct = iProductMapper.getProductById(productNumberDTO.getProductId());
            totalAmount += existProduct.getPrice() * productNumberDTO.getQuantity();
        }
        // create a invoice
        Invoice newInvoice = Invoice.builder()
                // should be auto get from backend
                .customerId(invoiceDTO.getCustomerId())
                .payMethod(invoiceDTO.getPayMethod())
                .orderStatus("ordered")
                .phoneNumber(invoiceDTO.getPhoneNumber())
                .address(invoiceDTO.getAddress())
                .fullName(invoiceDTO.getFullName())
                .totalAmount(totalAmount)
                .build();
        iInvoiceMapper.createInvoice(newInvoice);
        
        for (ProductNumberDTO invoiceProducts : invoiceDTO.getListProduct()) {
            // delete product purchared from cart
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!(principal instanceof Users)) {
                throw new RuntimeException("User not authenticated!");
            }
            Users user = (Users) principal;
            Long cartId = iCartMapper.getCartByUserId(user.getId());
            iCartProductMapper.deleteProductToCart(cartId, invoiceProducts.getProductId());
            //update quantity product
            Product existProduct = iProductMapper.getProductById(invoiceProducts.getProductId());
            Integer newQuantity = existProduct.getNumAvailable() - invoiceProducts.getQuantity();
            Product newProduct = Product.builder()
                    .id(existProduct.getId())
                    .numAvailable(newQuantity)
                    .build();
            iProductMapper.updateProduct(newProduct);
            // create a product into invoice
            InvoiceProduct newInvoiceProduct = InvoiceProduct.builder()
                    .invoiceId(newInvoice.getId())
                    .productId(invoiceProducts.getProductId())
                    .quantity(invoiceProducts.getQuantity())
                    .build();
            iInvoiceProductMapper.createInvoiceProduct(newInvoiceProduct);
        }

    }

    public List<ListInvoiceDisplay> gInvoiceDetail(Long userId) {
        List<ListInvoiceMapping> listInvoiceMapping = iInvoiceMapper.getAllInvoiceByUserId(userId);
        Map<Long, ListInvoiceDisplay> invocieMap = new LinkedHashMap<>();
        for (ListInvoiceMapping rowInvoiceMapping : listInvoiceMapping) {
            ListInvoiceDisplay invoice = invocieMap.get(rowInvoiceMapping.getOrderId());
            if (invoice == null) {
                invoice = new ListInvoiceDisplay();
                invoice.setOrderId(rowInvoiceMapping.getOrderId());
                invoice.setCustomerId(rowInvoiceMapping.getCustomerId());
                invoice.setPayMethod(rowInvoiceMapping.getPayMethod());
                invoice.setOrderStatus(rowInvoiceMapping.getOrderStatus());
                invoice.setCreatedAt(rowInvoiceMapping.getCreatedAt());
                invoice.setUpdatedAt(rowInvoiceMapping.getUpdatedAt());
                invoice.setTotalAmount(rowInvoiceMapping.getTotalAmount());
                invocieMap.put(rowInvoiceMapping.getOrderId(), invoice);
            }
            ListInvoiceDisplay.ProductInfo product = new ListInvoiceDisplay.ProductInfo();
            product.productId = rowInvoiceMapping.getProductId();
            product.productName = rowInvoiceMapping.getProductName();
            product.quantity = rowInvoiceMapping.getQuantity();

            invoice.listProduct.add(product);
        }
        return new ArrayList<>(invocieMap.values());
    }


    // public List<ListInvoiceDisplay> gInvoiceDetailByOrderId(Long orderId) {
    //     List<ListInvoiceMapping> listInvoiceMapping = iInvoiceMapper.exportInvoice(orderId);
    //     Map<Long, ListInvoiceDisplay> invocieMap = new HashMap<>();
    //     for (ListInvoiceMapping rowInvoiceMapping : listInvoiceMapping) {
    //         ListInvoiceDisplay invoice = invocieMap.get(rowInvoiceMapping.getOrderId());
    //         if (invoice == null) {
    //             invoice = new ListInvoiceDisplay();
    //             invoice.setOrderId(rowInvoiceMapping.getOrderId());
    //             invoice.setCustomerId(rowInvoiceMapping.getCustomerId());
    //             invoice.setFullName(rowInvoiceMapping.getFullName());
    //             invoice.setAddress(rowInvoiceMapping.getAddress());
    //             invoice.setPhoneNumber(rowInvoiceMapping.getPhoneNumber());
    //             invoice.setPayMethod(rowInvoiceMapping.getPayMethod());
    //             invoice.setOrderStatus(rowInvoiceMapping.getOrderStatus());
    //             invoice.setCreatedAt(rowInvoiceMapping.getCreatedAt());
    //             invoice.setUpdatedAt(rowInvoiceMapping.getUpdatedAt());
    //             invoice.setTotalAmount(rowInvoiceMapping.getTotalAmount());
    //             invocieMap.put(rowInvoiceMapping.getOrderId(), invoice);
    //         }
    //         ListInvoiceDisplay.ProductInfo product = new ListInvoiceDisplay.ProductInfo();
    //         product.productId = rowInvoiceMapping.getProductId();
    //         product.productName = rowInvoiceMapping.getProductName();
    //         product.quantity = rowInvoiceMapping.getQuantity();
    //         product.price = rowInvoiceMapping.getPrice();
    //         invoice.listProduct.add(product);
    //     }
    //     return new ArrayList<>(invocieMap.values());
    // }
    
    public List<ListInvoiceMapping> gInvoiceDetailByOrderId(Long orderId){
        return iInvoiceMapper.exportInvoice(orderId);
    }

    public byte[] exportInvoice(Long orderId) throws JRException, IOException {
        // Load file jrxml
        InputStream inputStream = getClass().getResourceAsStream("/reports/invoice_template.jrxml");

        // get Infor invoice for export invoice
        // List<ListInvoiceDisplay> listInvoiceDisplay = gInvoiceDetailByOrderId(orderId);


        List<ListInvoiceMapping> invoiceExported = gInvoiceDetailByOrderId(orderId);

        // Compile
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        // Dữ liệu chi tiết sản phẩm
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoiceExported);

        // Fill dữ liệu
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

        // Xuất ra PDF (trả về mảng byte)
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
    // Method test đơn giản
    // public String testJasperCompile() {
    //     try {
    //         InputStream inputStream = getClass().getResourceAsStream("/reports/invoice_template.jrxml");
    //         if (inputStream == null) {
    //             return "Lỗi: Không tìm thấy file JRXML";
    //         }
    //         JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
    //         inputStream.close();
    //         return "Thành công: Compile JasperReport OK";
    //     } catch (Exception e) {
    //         return "Lỗi: " + e.getMessage();
    //     }
    // }
}
