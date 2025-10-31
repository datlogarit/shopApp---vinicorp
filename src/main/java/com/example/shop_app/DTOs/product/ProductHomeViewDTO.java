package com.example.shop_app.DTOs.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * infor needed for home page 
 */ 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductHomeViewDTO {
    private Long id;

    private String name;

    private Long price;

    private String description;

    private String display_avt;
}
