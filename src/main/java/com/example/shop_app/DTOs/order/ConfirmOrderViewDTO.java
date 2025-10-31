package com.example.shop_app.DTOs.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * info needed for confirm order page
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConfirmOrderViewDTO {
    private String fullName;
    private String address;
    private String phoneNumber;
        
    private String productName;
    private Long price;
    private Integer quantity;
    private String imgUrl;
}
