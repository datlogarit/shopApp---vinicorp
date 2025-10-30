package com.example.shop_app.DTOs.invoice;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * all info relate invoice (direct mapping from query db)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListInvoiceMapping {
    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("address")
    private String address;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("product_name")
    private String productName;

    private Integer quantity;

    private Integer price;

    @JsonProperty("total_amount")
    private Long totalAmount;

    @JsonProperty("pay_method")
    private String payMethod;

    @JsonProperty("order_status")
    private String orderStatus;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    private Timestamp updatedAt;
}