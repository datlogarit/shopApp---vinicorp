package com.example.shop_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.requests.CartDTO;
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

    public void addToCart(CartDTO cartDTO){
        if (iCartMapper.existCartByUserId(cartDTO.getUserId()) == null) {
            // create cart
            iCartMapper.createNewCart(cartDTO);
        }
        // add product to cart
        
    }
    public void createNewCar(CartDTO cartDTO){
        
    }
}
// kiểm tra đã có cart chưa ?
//         - có rồi thì thêm bản ghi vào cart_product
//         - chưa có thì tạo cart mới, thêm bản ghi mới vào cart_product;
//         ---> cần thêm bản ghi cart mới, thêm bản ghi cart_product mới