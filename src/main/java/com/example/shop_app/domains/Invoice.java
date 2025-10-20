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
    private Long id;

    private Long customerId;

    private String payMethod;

    private String orderStatus;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String fullName;

    private String address;

    private String phoneNumber;

    private Long totalAmount;
}
