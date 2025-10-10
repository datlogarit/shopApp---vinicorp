package com.example.shop_app.DTOs.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
// response for request detail view
public class ProductDetailViewDTO {
    private Long id;

    private String name;

    private Long price;

    private String description;

    private Integer numAvailable;

    private String displayAvt;

    private List<String> images;
}
