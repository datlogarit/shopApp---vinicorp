package com.example.shop_app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shop_app.domains.Users;

@Mapper
public interface IUserMapper {
    public void crateNewUser(@Param("user") Users users);
    public Users getUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
