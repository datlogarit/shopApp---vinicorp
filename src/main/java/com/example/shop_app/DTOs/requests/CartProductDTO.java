package com.example.shop_app.DTOs.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDTO {
    private Integer cartId;
    private Integer productId;
    private Integer quantity;
}
