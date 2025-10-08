package com.example.shop_app.controllers.viewController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.shop_app.DTOs.cart.ProductToConfirm;
import com.example.shop_app.DTOs.orderConfirm.OrderConfirmViewDTO;
import com.example.shop_app.services.OrderService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderConfirmViewController {
    private final OrderService orderService;

    @PostMapping("")
    @ResponseBody
    private ResponseEntity<?> bindToOrderConfirm(@RequestBody List<ProductToConfirm> productToConfirm, HttpSession session) throws Exception {
        session.setAttribute("result", productToConfirm);
        return ResponseEntity.ok("Bind to server successfully");
    }

//    @SuppressWarnings("unchecked")
    @GetMapping("confirm")
    private String getOrderPage(Model model, HttpSession session) {
        List<ProductToConfirm> listProduct =(List<ProductToConfirm>) session.getAttribute("result");
        List<OrderConfirmViewDTO> orderConfirmViewDTOs = orderService.getDataOrderView(listProduct);
        model.addAttribute("items", orderConfirmViewDTOs);
        return "order-confirm";
    }
}