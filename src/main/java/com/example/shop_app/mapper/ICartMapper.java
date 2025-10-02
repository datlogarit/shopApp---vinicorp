package com.example.shop_app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shop_app.DTOs.responses.CartProductDTO;

@Mapper
public interface ICartMapper {
    public List<CartProductDTO> getInfoCartByUserId(@Param("userId") Long userId);
}
