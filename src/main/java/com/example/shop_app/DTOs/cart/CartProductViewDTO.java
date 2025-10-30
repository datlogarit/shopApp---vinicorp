package com.example.shop_app.DTOs.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * infor required to display the shopping cart
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductViewDTO {   
    private Long productId;

    private String productName;

    private Long price;

    private String displayAvt;

    private Integer quantity;

    private Integer numAvailable;
}
