package com.example.shop_app.controllers.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthViewController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Trả về file login.html
    }

    
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Trả về file register.html
    }
}

