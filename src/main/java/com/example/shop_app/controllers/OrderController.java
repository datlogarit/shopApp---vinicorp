package com.example.shop_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("api/v1/order")
// @RequiredArgsConstructor
public class OrderController {
    // private final OrderService orderService;

    @GetMapping("")
    private String oderPage() {
        return "order";
    }
}
