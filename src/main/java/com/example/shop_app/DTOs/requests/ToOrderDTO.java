package com.example.shop_app.DTOs.requests;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToOrderDTO {
    private Integer totalCost;
    private List<OrderProductDTO> products;
}
