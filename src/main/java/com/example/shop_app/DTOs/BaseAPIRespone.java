package com.example.shop_app.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * standard response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseAPIRespone<T> {
    private int status;
    private String message;
    private T data;
}
