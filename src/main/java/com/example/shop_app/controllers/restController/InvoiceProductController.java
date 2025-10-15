package com.example.shop_app.controllers.restController;

import com.example.shop_app.DTOs.BaseAPIRespone;
import com.example.shop_app.DTOs.invoiceProduct.InvoiceProductDTO;
import com.example.shop_app.services.InvoiceProductService;

// import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InvoiceProductController {
    private final InvoiceProductService invoiceProductService;

    public ResponseEntity<?> initProductInInvoice(@RequestBody InvoiceProductDTO invoiceProductDTO){
        invoiceProductService.initProductInInvoice(invoiceProductDTO);
        return ResponseEntity.ok(new BaseAPIRespone<>(200, "success", "create successfully"));
    }
    
} 