package com.example.shop_app.controllers.viewController;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.shop_app.services.ProductService;
import com.example.shop_app.DTOs.product.ProductHomeViewDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeViewController{
    // return home page and related home page;
    private final ProductService productService;

    @GetMapping("")
    public String getHome(Model model) {
        List<ProductHomeViewDTO> allProduct = productService.getAllProducts();
        model.addAttribute("allProduct", allProduct);
        return "home";
    }
}
