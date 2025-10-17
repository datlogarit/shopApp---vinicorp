package com.example.shop_app.DTOs.invoice;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailViewDTO {
    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("product_name")
    private String productName;

    private Integer quantity;

    @JsonProperty("pay_method")
    private String payMethod;

    @JsonProperty("order_status")
    private String orderStatus;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    private Timestamp updatedAt;
}