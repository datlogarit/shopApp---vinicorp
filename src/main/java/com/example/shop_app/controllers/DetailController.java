package com.example.shop_app.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shop_app.services.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1/detail")
@RequiredArgsConstructor
public class DetailController {
    private final ProductService productService;

    @GetMapping("")
    public String getDetail(Model model){
        // List<Product> allProduct = productService.getAllProducts();
        // model.addAttribute("allProduct", allProduct);
        return "detail";
    }
}

