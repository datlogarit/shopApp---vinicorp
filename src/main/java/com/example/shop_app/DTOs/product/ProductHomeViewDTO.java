package com.example.shop_app.DTOs.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//response for request home view 
public class ProductHomeViewDTO {
    private Long id;

    private String name;

    private Long price;

    private String description;

    private String display_avt;
}
