package com.example.shop_app.DTOs.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceDTO {
    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("pay_method")
    private String payMethod;

    @JsonProperty("order_status")
    private String orderStatus;
}
