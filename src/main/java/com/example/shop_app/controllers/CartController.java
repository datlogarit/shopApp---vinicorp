package com.example.shop_app.controllers;

import java.util.List;

import com.example.shop_app.DTOs.cart.CartProductViewDTO;
import com.example.shop_app.DTOs.requests.CartProductDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.shop_app.services.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{userId}")
    private ResponseEntity<?> getInfoCart(@PathVariable Long userId){
        List<CartProductViewDTO> listCartProductDTOs = cartService.getInfoCart(userId);
        return ResponseEntity.ok(listCartProductDTOs);
    }

    @PostMapping("/add")
    private ResponseEntity<?> addToCart(@RequestParam(name = "userId") Long userId, @RequestBody CartProductDTO cartProductDTO){
        cartService.addToCart(userId, cartProductDTO);
        return ResponseEntity.ok("Add to cart successfully!");
    }
}
