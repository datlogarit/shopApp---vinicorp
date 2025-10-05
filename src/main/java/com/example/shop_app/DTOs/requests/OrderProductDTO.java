package com.example.shop_app.DTOs.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProductDTO {
    private String imgUrl;
    private String productName;
    private Integer price;
    private Integer quantity;
}
