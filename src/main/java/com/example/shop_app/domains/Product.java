package com.example.shop_app.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * product domain
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;

    private String name;

    private Long price;

    private String description;

    private Integer numAvailable;
    
    private String displayAvt;
}
