package com.example.shop_app.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProduct {
    private Long cartId;
    private Long productId;
    private Integer quantity;
}
