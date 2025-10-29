package com.example.shop_app.controllers.viewController;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shop_app.DTOs.product.ProductDetailViewDTO;
import com.example.shop_app.domains.Users;
import com.example.shop_app.services.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1/detail")
@RequiredArgsConstructor
public class DetailViewController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public String getDetail(Model model, @PathVariable(name = "productId") Long productId, @AuthenticationPrincipal Users userDetails) {
        model.addAttribute("userId", userDetails != null ? userDetails.getId() : null);

        ProductDetailViewDTO detailProduct = productService.getDetailProduct(productId);
        model.addAttribute("detailPr", detailProduct);
        return "detail";
    }
}
