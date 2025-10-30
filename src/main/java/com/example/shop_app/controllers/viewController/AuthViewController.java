package com.example.shop_app.controllers.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
/**
 * handle scren authen - login, register
 */
public class AuthViewController {
    /**
     * redirect to home page when user just new access webpage
     * @return
    */
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/api/v1/home";
    }
    
    /**
     * request login page
     * @return
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }

    /**
     * request register page
     * @return
     */
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
}