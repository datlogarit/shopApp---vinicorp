package com.example.shop_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shop_app.DTOs.responses.DetailProductDTO;
import com.example.shop_app.services.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1/detail")
@RequiredArgsConstructor
public class DetailController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public String getDetail(Model model, @PathVariable(name = "productId") Long productId) {
        DetailProductDTO detailProduct = productService.getDetailProduct(productId);
        model.addAttribute("detailPr", detailProduct);
        return "detail";
    }
}
