package com.example.shop_app.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceProduct {
    private Long orderId;

    private Long productId;
    
    private Integer quantity;
}
