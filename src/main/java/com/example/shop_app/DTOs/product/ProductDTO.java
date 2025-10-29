package com.example.shop_app.DTOs.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//update quantity
public class ProductDTO {
    private Long id;

    private String name;

    private Long price;

    private String description;

    @JsonProperty("num_available")
    private Integer numAvailable;
    
    private String displayAvt;
}
