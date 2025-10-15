package com.example.shop_app.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Used to respond to clients in a consistent manner (response message, response object, response error)
public class BaseAPIRespone<T> {
    private int status;
    private String message;
    private T data;
}
