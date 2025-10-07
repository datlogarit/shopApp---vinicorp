package com.example.shop_app.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.shop_app.services.ProductService;
import com.example.shop_app.DTOs.responses.DetailProductViewDTO;
import com.example.shop_app.domains.Product;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public String getHome(Model model) {
        List<Product> allProduct = productService.getAllProducts();
        model.addAttribute("allProduct", allProduct);
        return "home";
    }

    @GetMapping("/{productId}")
    public String getDetail(Model model, @PathVariable(name = "productId") Long productId) {
        DetailProductViewDTO detailProduct = productService.getDetailProduct(productId);
        model.addAttribute("detailPr", detailProduct);
        return "detail";
    }
}
