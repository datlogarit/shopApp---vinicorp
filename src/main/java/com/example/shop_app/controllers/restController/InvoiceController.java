package com.example.shop_app.controllers.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_app.DTOs.BaseAPIRespone;
import com.example.shop_app.DTOs.invoice.InvoiceDTO;
import com.example.shop_app.services.InvoiceService;

import lombok.RequiredArgsConstructor;

// invoice entity equivalent order entity
@RestController
@RequestMapping("api/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    // api is called when init invoice
    @PostMapping("")
    public ResponseEntity<?> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        invoiceService.createInvoice(invoiceDTO);
        ;
        return ResponseEntity.ok(new BaseAPIRespone<>(200, "success", "create invoice successfully"));
    }
}
