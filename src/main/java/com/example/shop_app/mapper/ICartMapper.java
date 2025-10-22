package com.example.shop_app.mapper;

import com.example.shop_app.domains.Cart;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ICartMapper {
    
    public Long existCartByUserId(@Param("userId") Long userId);

    public void createNewCart(@Param("cart") Cart cart);

    public Long getCartByUserId(@Param("userId") Long userId);

}