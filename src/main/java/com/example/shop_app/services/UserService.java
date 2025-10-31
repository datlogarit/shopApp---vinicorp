package com.example.shop_app.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.user.UserDTO;
import com.example.shop_app.domains.Users;
import com.example.shop_app.mapper.IUserMapper;

import lombok.RequiredArgsConstructor;

/**
 * service handle request related user
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserMapper iUserMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * create a new user
     * @param userDTO -info needed for register request
     */
    public void createUser(UserDTO userDTO){
        Users existUsers = iUserMapper.getUserByPhoneNumber(userDTO.getPhoneNumber());
        if(existUsers!=null) throw new RuntimeException("The phone number already in user");
        Users users = Users.builder()
        .fullName(userDTO.getFullName())
        .phoneNumber(userDTO.getPhoneNumber())
        .password(passwordEncoder.encode(userDTO.getPassword()))
        .address(userDTO.getAddress())
        .role("customer")
        .build();
        iUserMapper.crateNewUser(users);
    }
}
