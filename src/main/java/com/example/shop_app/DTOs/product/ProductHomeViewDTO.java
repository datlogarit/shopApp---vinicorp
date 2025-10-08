package com.example.shop_app.DTOs.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductHomeViewDTO {
    private Long id;
    private String name;
    private Long price;
    private String description;
    private String display_avt;
}
