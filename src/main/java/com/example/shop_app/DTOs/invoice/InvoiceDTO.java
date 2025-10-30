package com.example.shop_app.DTOs.invoice;

import java.util.List;

import com.example.shop_app.DTOs.product.ProductNumberDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * request to create new invoice
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceDTO {
    @NotBlank(message = "This field cannot be left blank.")
    @JsonProperty("pay_method")
    private String payMethod;

    @JsonProperty("order_status")
    private String orderStatus;

    @NotBlank(message = "This field cannot be left blank.")
    @JsonProperty("full_name")
    private String fullName;

    @NotBlank(message = "This field cannot be left blank.")
    private String address;

    @NotBlank(message = "This field cannot be left blank.")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @Valid
    @NotEmpty(message = "This field cannot be left blank.")
    @JsonProperty("list_product")
    private List<ProductNumberDTO> listProduct;
}
