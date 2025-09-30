package com.example.shop_app.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private Long price;
    private String description;
    private Integer num_available; 
    private String display_avt;
}
