package com.example.shop_app.DTOs.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDTO {
    @JsonProperty("cart_id")
    private Long cartId;

    @JsonProperty("product_id")
    private Long productId;

    private Integer quantity;
}
