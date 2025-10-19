package com.example.shop_app.controllers.restController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import org.apache.tomcat.util.http.parser.MediaType;
// import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_app.DTOs.BaseAPIRespone;
import com.example.shop_app.DTOs.invoice.InvoiceDTO;
import com.example.shop_app.DTOs.invoice.ListInvoiceDisplay;
import com.example.shop_app.domains.Product;
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

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllInvoice(@PathVariable("userId") Long userId) {
        List<ListInvoiceDisplay> listInvoice = invoiceService.gInvoiceDetail(userId);
        ;
        return ResponseEntity.ok(new BaseAPIRespone<>(200, "success", listInvoice));
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportInvoice() throws Exception {
        // Giả lập dữ liệu
        List<Product> products = List.of(
                new Product(1L, "Kính cận", 100000L, "this is test", 2002, "avt"),
                new Product(2L, "Kính cận 2", 100000L, "this is test", 2001, "avt"));

        Map<String, Object> params = new HashMap<>();
        params.put("customerName", "Nguyễn Văn A");
        params.put("customerAddress", "123 Trần Hưng Đạo, Hà Nội");
        params.put("customerPhone", "0123456789");
        params.put("totalAmount", 700000.0);

        byte[] pdfBytes = invoiceService.exportInvoice(products, params);

        return ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
