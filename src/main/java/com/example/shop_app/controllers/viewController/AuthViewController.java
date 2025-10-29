package com.example.shop_app.controllers.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthViewController {

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/api/v1/home";
    }
    
    // request that return the login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }

    // request that return the register page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
}