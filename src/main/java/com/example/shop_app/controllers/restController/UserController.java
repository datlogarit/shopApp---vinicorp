package com.example.shop_app.controllers.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_app.DTOs.BaseAPIRespone;
import com.example.shop_app.DTOs.user.UserDTO;
import com.example.shop_app.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("signUp")
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO){
         userService.createUser(userDTO);;
         return ResponseEntity.ok(new BaseAPIRespone<>(200, "success", "create user successfully"));
    }
}
