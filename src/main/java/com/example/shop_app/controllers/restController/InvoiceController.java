package com.example.shop_app.controllers.restController;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_app.DTOs.BaseAPIRespone;
import com.example.shop_app.DTOs.invoice.InvoiceDTO;
import com.example.shop_app.domains.Users;
import com.example.shop_app.services.InvoiceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// invoice entity equivalent order entity
@RestController
@RequestMapping("api/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    // api is called when init invoice
    @PostMapping("")
    public ResponseEntity<?> createInvoice(@AuthenticationPrincipal Users userDetails, @Valid @RequestBody InvoiceDTO invoiceDTO) {
        invoiceService.createInvoice(userDetails.getId(), invoiceDTO);
        return ResponseEntity.ok(new BaseAPIRespone<>(200, "success", "create invoice successfully"));
    }

    @GetMapping("/export/{invoiceId}")
    public ResponseEntity<byte[]> exportInvoice(@PathVariable("invoiceId") Long invoiceId, @AuthenticationPrincipal Users userDetails) throws Exception {
        byte[] pdfBytes = invoiceService.exportInvoice(invoiceId, userDetails.getId());
        return ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
