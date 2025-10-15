package com.example.shop_app.controllers.viewController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.shop_app.DTOs.BaseAPIRespone;
import com.example.shop_app.DTOs.order.ConfirmOrderViewDTO;
import com.example.shop_app.DTOs.product.ProductNumberDTO;
import com.example.shop_app.domains.Users;
import com.example.shop_app.services.OrderService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class ConfirmOrderViewController {
    private final OrderService orderService;

    @PostMapping("")
    @ResponseBody
    // bind list product to server
    private ResponseEntity<?> bindToOrderConfirm(@RequestBody List<ProductNumberDTO> productToConfirm,
            HttpSession session) throws Exception {
        session.setAttribute("result", productToConfirm);
        return ResponseEntity.ok(new BaseAPIRespone<>(200, "success", "Bind to server successfully"));
    }

    //server - response
    @SuppressWarnings("unchecked")
    @GetMapping("confirm")
    private String getOrderPage(Model model, HttpSession session, @AuthenticationPrincipal Users userDetails) {
        List<ProductNumberDTO> listProduct = (List<ProductNumberDTO>) session.getAttribute("result");
        // ProductNumberDTO to OrderConfirmViewDTO (view)
        List<ConfirmOrderViewDTO> orderConfirmViewDTOs = orderService.getDataOrderView(listProduct);
        Long totalPrice = orderService.caculateTotal(listProduct);
        model.addAttribute("userId", userDetails.getId());
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("items", orderConfirmViewDTOs);
        return "order-confirm";
    }
}