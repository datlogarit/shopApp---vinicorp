package com.example.shop_app.DTOs.orderConfirm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderConfirmViewDTO {
    private String productName;
    private Long price;
    private Integer quantity;
    private String imgUrl;
}
