package com.example.shop_app.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_app.DTOs.responses.CartProductDTO;
import com.example.shop_app.services.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @GetMapping("/{userId}")
    private ResponseEntity<?> getInfoCart(@PathVariable Long userId){
        List<CartProductDTO> listCartProductDTOs = cartService.getInfoCart(userId);
        return ResponseEntity.ok(listCartProductDTOs);
    }
}
