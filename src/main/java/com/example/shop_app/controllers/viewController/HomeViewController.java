package com.example.shop_app.controllers.viewController;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.shop_app.services.ProductService;
import com.example.shop_app.DTOs.product.ProductHomeViewDTO;
import com.example.shop_app.domains.Users;

import lombok.RequiredArgsConstructor;

/**
 * requests related home page
 */
@Controller
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeViewController{
    private final ProductService productService;

    /**
     * request to home page
     * @param model
     * @param userDetails
     * @return
     */
    @GetMapping("")
    public String getHome(Model model, @AuthenticationPrincipal Users userDetails) {
        model.addAttribute("userId", userDetails != null ? userDetails.getId() : null);
        List<ProductHomeViewDTO> allProduct = productService.getAllProducts();
       
        model.addAttribute("allProduct", allProduct);
        return "home";
    }
}
