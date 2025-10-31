package com.example.shop_app.DTOs.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * info needed for register request
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    @NotBlank(message = "This field cannot be left blank.")
    @JsonProperty("full_name")
    private String fullName;

    @NotBlank(message = "This field cannot be left blank.")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotBlank(message = "This field cannot be left blank.")
    private String password;

    @NotBlank(message = "This field cannot be left blank.")
    private String address;
    
    private String role; 
}
