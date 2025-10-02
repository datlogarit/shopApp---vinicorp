package com.example.shop_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.responses.CartProductDTO;
import com.example.shop_app.mapper.ICartMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ICartMapper iCartMapper;

    public List<CartProductDTO> getInfoCart(Long userId){
        return iCartMapper.getInfoCartByUserId(userId);
    }
}
