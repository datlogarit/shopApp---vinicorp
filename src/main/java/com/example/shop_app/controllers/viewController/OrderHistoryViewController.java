package com.example.shop_app.controllers.viewController;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shop_app.DTOs.invoice.ListInvoiceDisplay;
import com.example.shop_app.domains.Users;
import com.example.shop_app.services.InvoiceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("api/v1/order-history")
@RequiredArgsConstructor
public class OrderHistoryViewController {
    private final InvoiceService invoiceService;

    @GetMapping("")
    public String getViewOrderHistory(Model model, @AuthenticationPrincipal Users userDetails) {
        // Giả lập dữ liệu
        // List<Product> products = List.of(
        //         new Product(1L, "Kính cận", 100000L, "this is test", 2002, "avt"),
        //         new Product(2L, "Kính cận 2", 100000L, "this is test", 2001, "avt"));

        // Map<String, Object> params = new HashMap<>();
        // params.put("customerName", "Nguyễn Văn A");
        // params.put("customerAddress", "123 Trần Hưng Đạo, Hà Nội");
        // params.put("customerPhone", "0123456789");
        // params.put("totalAmount", 700000.0);
        List<ListInvoiceDisplay> listInvoice = invoiceService.gInvoiceDetail(userDetails.getId());
        ;
        model.addAttribute("listInvoice", listInvoice);
        model.addAttribute("userId", userDetails.getId());
        return "order-history";
    }
}
