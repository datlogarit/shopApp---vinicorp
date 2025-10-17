package com.example.shop_app.controllers.viewController;

// package com.example.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/api/v1/home";
    }
}

