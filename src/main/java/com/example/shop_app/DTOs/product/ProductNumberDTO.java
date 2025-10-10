package com.example.shop_app.DTOs.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//for request and response need number of product
public class ProductNumberDTO {
    @JsonProperty("product_id")
    private Long productId;

    private Integer quantity;
}