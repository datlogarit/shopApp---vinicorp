package com.example.shop_app.DTOs.invoiceProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceProductDTO {
    private Long invoiceId;
    private Long productId;
    private Integer quantity;
}
