package com.example.shop_app.controllers.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * handle scren authen - login, register page
 */
@Controller
public class AuthViewController {
    /**
     * redirect to home page when user just new access webpage
     * @return home page
    */
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/api/v1/home";
    }
    
    /**
     * request login page
     * @return login page
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }

    /**
     * request register page
     * @return register page
     */
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
}