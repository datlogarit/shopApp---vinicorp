package com.example.shop_app.DTOs.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetailProductDTO {
    private Long id;
    private String name;
    private Long price;
    private String description;
    private Integer numAvailable;
    private String displayAvt;
    private List<String> images;
}
