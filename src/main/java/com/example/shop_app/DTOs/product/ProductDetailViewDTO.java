package com.example.shop_app.DTOs.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * infor for detail pages
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDetailViewDTO {
    private Long id;

    private String name;

    private Long price;

    private String description;

    private Integer numAvailable;

    private String displayAvt;

    private List<String> images;
}
