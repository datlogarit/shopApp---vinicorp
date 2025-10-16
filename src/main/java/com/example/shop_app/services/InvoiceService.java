package com.example.shop_app.services;

import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.invoice.InvoiceDTO;
import com.example.shop_app.DTOs.product.ProductNumberDTO;
import com.example.shop_app.domains.Invoice;
import com.example.shop_app.domains.InvoiceProduct;
import com.example.shop_app.domains.Product;
import com.example.shop_app.mapper.IInvoiceMapper;
import com.example.shop_app.mapper.IInvoiceProductMapper;
import com.example.shop_app.mapper.IProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final IInvoiceMapper iInvoiceMapper;
    private final IInvoiceProductMapper iInvoiceProductMapper;
    private final IProductMapper iProductMapper;

    public void createInvoice(InvoiceDTO invoiceDTO) {
        // create a invoice
        Invoice newInvoice = Invoice.builder()
                // should be auto get from backend
                .customerId(invoiceDTO.getCustomerId())
                .payMethod(invoiceDTO.getPayMethod())
                .orderStatus("ordered")
                .phoneNumber(invoiceDTO.getPhoneNumber())
                .address(invoiceDTO.getAddress())
                .fullName(invoiceDTO.getFullName())
                .build();
                iInvoiceMapper.createInvoice(newInvoice);
        // create a product into invoice
        for (ProductNumberDTO invoiceProducts : invoiceDTO.getListProduct()) {
            Product existProduct = iProductMapper.getProductById(invoiceProducts.getProductId());
            Integer newQuantity = existProduct.getNumAvailable() - invoiceProducts.getQuantity();
            Product newProduct = Product.builder()
            .id(existProduct.getId())
            .numAvailable(newQuantity)
            .build();
            iProductMapper.updateProduct(newProduct);
            InvoiceProduct newInvoiceProduct = InvoiceProduct.builder()
            .invoiceId(newInvoice.getId())
            .productId(invoiceProducts.getProductId())
            .quantity(invoiceProducts.getQuantity())
            .build();
            iInvoiceProductMapper.createInvoiceProduct(newInvoiceProduct);
        }
        
    }
}
