package com.example.shop_app.controllers.viewController;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shop_app.DTOs.product.ProductHomeViewDTO;
import com.example.shop_app.domains.Users;
import com.example.shop_app.services.ProductService;

import lombok.RequiredArgsConstructor;

/**
 * request related search page
 */
@Controller
@RequestMapping("api/v1/search")
@RequiredArgsConstructor
public class SearchController {
    private final ProductService productService;

    /**
     * search by key request
     * @param keyword - keyword need to search
     * @param model
     * @param userDetails
     * @return
     */
    @GetMapping("")
    public String getKey(@RequestParam("keyword") String keyword, Model model, @AuthenticationPrincipal Users userDetails){
        model.addAttribute("userId", userDetails != null ? userDetails.getId() : null);

        List<ProductHomeViewDTO> result = productService.getProductByName(keyword);
        model.addAttribute("products", result);
        model.addAttribute("keyword", keyword);
        return "search-view";
    }
}
