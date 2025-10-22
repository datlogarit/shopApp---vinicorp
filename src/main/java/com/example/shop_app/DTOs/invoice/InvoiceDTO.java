package com.example.shop_app.DTOs.invoice;

import java.util.List;

import com.example.shop_app.DTOs.product.ProductNumberDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceDTO {
    // @JsonProperty("customer_id")
    // private Long customerId;

    @JsonProperty("pay_method")
    private String payMethod;

    @JsonProperty("order_status")
    private String orderStatus;

    @JsonProperty("full_name")
    private String fullName;

    private String address;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("list_product")
    private List<ProductNumberDTO> listProduct;
}
