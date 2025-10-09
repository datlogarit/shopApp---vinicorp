package com.example.shop_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.cart.CartProductViewDTO;
import com.example.shop_app.mapper.ICartMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ICartMapper iCartMapper;

    // vì phải join 3 bảng nên trả về view trực tiếp
    public List<CartProductViewDTO> getInfoCartByUserId(Long userId) {
        return iCartMapper.getInfoCartByUserId(userId);
    }
}