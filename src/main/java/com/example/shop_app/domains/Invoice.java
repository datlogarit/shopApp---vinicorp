package com.example.shop_app.domains;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    private Long orderId;

    private Long customerId;

    private String payMethod;

    private String orderStatus;

    private Long totalAmount;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
