package com.example.shop_app.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop_app.DTOs.invoice.InvoiceDTO;
import com.example.shop_app.DTOs.invoice.ListInvoiceView;
import com.example.shop_app.DTOs.invoice.ListInvoiceMapping;
import com.example.shop_app.DTOs.product.ProductNumberDTO;
import com.example.shop_app.domains.Invoice;
import com.example.shop_app.domains.InvoiceProduct;
import com.example.shop_app.domains.Product;
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

    // các cách để giải quyết vấn đề race condition
    // 1. update database để kiểm tra số lượng sản phẩm tồn kho trực tiếp trong db
    // 2. khóa bi quan - khóa lạc quan: khóa khi gặp luồng thực thi có thể gây race condition
    // 3. 
    @Transactional
    public void createInvoice(Long customerId, InvoiceDTO invoiceDTO) {
        //caculate total amount 
        Long totalAmount = 0L;
        for (ProductNumberDTO productNumberDTO : invoiceDTO.getListProduct()) {
            // 🔒 Lấy bản ghi có khóa
            Product existProduct = iProductMapper.getProductByIdForUpdate(productNumberDTO.getProductId());
            
            if (productNumberDTO.getQuantity() > existProduct.getNumAvailable()) {
                throw new RuntimeException("This product is currently out of stock due to high demand. Please try again.");
            } else {
                totalAmount += existProduct.getPrice() * productNumberDTO.getQuantity();
            }
        }
        
        // create a invoice
        Invoice newInvoice = Invoice.builder()
                // should be auto get from backend
                .customerId(customerId)
                .payMethod(invoiceDTO.getPayMethod())
                .orderStatus("ordered")
                .phoneNumber(invoiceDTO.getPhoneNumber())
                .address(invoiceDTO.getAddress())
                .fullName(invoiceDTO.getFullName())
                .totalAmount(totalAmount)
                .build();
        iInvoiceMapper.createInvoice(newInvoice);
        
        for (ProductNumberDTO invoiceProducts : invoiceDTO.getListProduct()) {
            Long cartId = iCartMapper.getCartByUserId(customerId);
            iCartProductMapper.deleteProductIntoCart(cartId, invoiceProducts.getProductId());
        
            // 🔒 Lấy lại bản ghi có khóa để chắc chắn chưa thay đổi
            Product existProduct = iProductMapper.getProductByIdForUpdate(invoiceProducts.getProductId());
            Integer newQuantity = existProduct.getNumAvailable() - invoiceProducts.getQuantity();
            
            if (newQuantity < 0) {
                throw new RuntimeException("This product just went out of stock, please refresh your cart.");
            }
        
            Product newProduct = Product.builder()
                    .id(existProduct.getId())
                    .numAvailable(newQuantity)
                    .build();
            iProductMapper.updateProduct(newProduct);
        
            // create invoice product
            InvoiceProduct newInvoiceProduct = InvoiceProduct.builder()
                    .invoiceId(newInvoice.getId())
                    .productId(invoiceProducts.getProductId())
                    .quantity(invoiceProducts.getQuantity())
                    .build();
            iInvoiceProductMapper.createInvoiceProduct(newInvoiceProduct);
        }        
    }

    public List<ListInvoiceView> gListInvoiceDetail(Long userId) {
        List<ListInvoiceView> listInvoiceMapping = iInvoiceMapper.getAllInvoiceByUserId(userId);
        return listInvoiceMapping;
    }
    
    public List<ListInvoiceMapping> gInvoiceDetailByOrderId(Long orderId){
        return iInvoiceMapper.getInfoExportInvoice(orderId);
    }

    public byte[] exportInvoice(Long invoiceId, Long userId) throws JRException, IOException {
        //xem invoice có thuộc người dùng hiện tại không ?
        if (iInvoiceMapper.checkExistByInvoiceIdAndUserId(userId, invoiceId)==null) {
            throw new RuntimeException("You do not have permission to access this file");
        }
        // Load file jrxml from to path of jrxml file.
        InputStream inputStream = getClass().getResourceAsStream("/reports/invoice_template.jrxml");

        // product invoice
        List<ListInvoiceMapping> invoiceExported = gInvoiceDetailByOrderId(invoiceId);

        // Compile
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        // detail Data of invoice
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoiceExported);

        // Fill data
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

        // Export to PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
