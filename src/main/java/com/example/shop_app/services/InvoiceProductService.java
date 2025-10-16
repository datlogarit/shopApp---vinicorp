// package com.example.shop_app.services;

// import org.springframework.stereotype.Service;

// import com.example.shop_app.DTOs.invoiceProduct.InvoiceProductDTO;
// import com.example.shop_app.domains.InvoiceProduct;
// import com.example.shop_app.mapper.IInvoiceProductMapper;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class InvoiceProductService {
//     private final IInvoiceProductMapper iInvoiceProductMapper;
//     public void initProductInInvoice(InvoiceProductDTO invoiceProductDTO){
//         InvoiceProduct invoiceProduct = InvoiceProduct.builder().build();
//         iInvoiceProductMapper.createInvoiceProduct(invoiceProduct);
//     }
// }
