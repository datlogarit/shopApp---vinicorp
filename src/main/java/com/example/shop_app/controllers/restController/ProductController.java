package com.example.shop_app.controllers.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_app.DTOs.product.ProductDTO;
import com.example.shop_app.services.ProductService;

import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/product")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PutMapping("")
    public ResponseEntity<?> updateQuantity(@RequestBody ProductDTO productDTO){
        productService.updateQuantityProduct(productDTO);
        return ResponseEntity.ok("update successfully");
    }
}
