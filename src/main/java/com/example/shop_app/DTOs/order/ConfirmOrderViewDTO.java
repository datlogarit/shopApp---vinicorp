package com.example.shop_app.DTOs.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
//confirm order view
public class ConfirmOrderViewDTO {
    private String fullName;
    private String address;
    private String phoneNumber;
        
    private String productName;
    private Long price;
    private Integer quantity;
    private String imgUrl;
}
