package com.example.shop_app.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_app.DTOs.requests.CartDTO;
import com.example.shop_app.DTOs.responses.CartProductDTO;
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
        List<CartProductDTO> listCartProductDTOs = cartService.getInfoCart(userId);
        return ResponseEntity.ok(listCartProductDTOs);
    }

    @PostMapping("add")
    @ResponseBody
    private ResponseEntity<?> addToCart(@RequestBody CartDTO cartDTO){
        cartService.addToCart(cartDTO);
        return ResponseEntity.ok("Add to cart successfully!");
    }
}
