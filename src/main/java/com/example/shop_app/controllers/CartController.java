package com.example.shop_app.controllers;

import java.util.List;

import com.example.shop_app.DTOs.responses.CartProductViewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.shop_app.services.CartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{userId}")
    @ResponseBody
    private ResponseEntity<?> getInfoCart(@PathVariable Long userId){
        List<CartProductViewDTO> listCartProductDTOs = cartService.getInfoCart(userId);
        return ResponseEntity.ok(listCartProductDTOs);
    }

    @PostMapping("add")
    @ResponseBody
    private ResponseEntity<?> addToCart(@RequestParam(name = "userId") Long userId, @RequestBody com.example.shop_app.DTOs.requests.CartProductDTO cartProductDTO){
        cartService.addToCart(userId, cartProductDTO);
        return ResponseEntity.ok("Add to cart successfully!");
    }
}
