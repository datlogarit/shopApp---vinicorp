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

    // because have to join 3 table so return the derect view;
    public List<CartProductViewDTO> getInfoCartByUserId(Long userId) {
        return iCartMapper.getInfoCartByUserId(userId);
    }
}