package com.example.shop_app.DTOs.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * number of specific product
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductNumberDTO {
    @NotNull(message = "This field cannot be left blank.")
    @JsonProperty("product_id")
    private Long productId;

    @Min(value = 1, message = "Product number must be greate than 0")
    @NotNull(message = "This field cannot be left blank.")
    private Integer quantity;
}