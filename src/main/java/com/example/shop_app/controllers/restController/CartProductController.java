package com.example.shop_app.controllers.restController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_app.DTOs.BaseAPIRespone;
import com.example.shop_app.DTOs.cart.CartProductViewDTO;
import com.example.shop_app.DTOs.product.ProductNumberDTO;
import com.example.shop_app.domains.Users;
import com.example.shop_app.services.CartProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart-product")
// handle requests related to products in the shopping cart
public class CartProductController {
    private final CartProductService cartProductService;

    // get product in shopping cart
    @GetMapping("")
    private ResponseEntity<?> getInfoCart(@AuthenticationPrincipal Users userDetails){
        List<CartProductViewDTO> cartProducts = cartProductService.getProductInCart(userDetails.getId());
        return ResponseEntity.ok(new BaseAPIRespone<>(200, "success", cartProducts));
    }

    // add product to shopping cart
    @PostMapping("/add")
    private ResponseEntity<?> addProductToCart(
            @AuthenticationPrincipal Users userDetails,
            @Valid @RequestBody ProductNumberDTO productInfo) {
        cartProductService.addProductToCart(userDetails.getId(), productInfo);
        return ResponseEntity.ok(new BaseAPIRespone<>(200, "success", "Add product to cart successfully"));
    }

    //delete product in the shoping cart
    @DeleteMapping("/delete/{productId}")
    private ResponseEntity<?> deleteProductToCart(
            @AuthenticationPrincipal Users userDetails,
            @PathVariable("productId") Long productId) {
        cartProductService.deleteProductToCart(userDetails.getId(), productId);
        return ResponseEntity.ok(new BaseAPIRespone<>(200, "success", "delete product to cart successfully"));
    }
}