package com.example.shop_app.controllers.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_app.DTOs.BaseAPIRespone;
import com.example.shop_app.DTOs.product.ProductNumberDTO;
import com.example.shop_app.domains.CartProduct;
import com.example.shop_app.domains.Users;
import com.example.shop_app.services.CartProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart-product")
public class CartProductController {
    private final CartProductService cartProductService;

    @PostMapping("/add")
    private ResponseEntity<?> addProductToCart(
            @RequestParam(name = "userId") Long userId,
            @RequestBody ProductNumberDTO addToCartRequest) {
        cartProductService.addToCart(userId, addToCartRequest);
        return ResponseEntity.ok(new BaseAPIRespone<>(200, "success", "add product to cart successfully"));
    }

    @DeleteMapping("/delete/{productId}")
    private ResponseEntity<?> deleteProductToCart(
            @AuthenticationPrincipal Users userDetails,
            @PathVariable("productId") Long productId) {
        cartProductService.deleteProductToCart(userDetails.getId(), productId);
        return ResponseEntity.ok(new BaseAPIRespone<>(200, "success", "delete product to cart successfully"));
    }

    @PutMapping("/update")
    private ResponseEntity<?> update(
            @RequestParam("cartId") Long cartId,
            @RequestParam("productId") Long productId,
            @RequestBody CartProduct addToCartRequest) {
        cartProductService.updateCartProduct(cartId, productId, addToCartRequest);
        return ResponseEntity.ok(new BaseAPIRespone<>(200, "success", "Update product in cart successfully!"));
    }
}