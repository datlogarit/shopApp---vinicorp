package com.example.shop_app.services;

import java.util.List;

import com.example.shop_app.DTOs.responses.CartProductViewDTO;
import com.example.shop_app.domains.Cart;
import com.example.shop_app.domains.CartProduct;

import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.requests.CartDTO;
import com.example.shop_app.DTOs.requests.CartProductDTO;
import com.example.shop_app.mapper.ICartMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ICartMapper iCartMapper;

    public List<CartProductViewDTO> getInfoCart(Long userId) {
        return iCartMapper.getInfoCartByUserId(userId);
    }

    public void addToCart(Long userId, CartProductDTO cartProductDTO) {
        if (iCartMapper.existCartByUserId(userId) == null) {
            // create cart if cart not exist
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            iCartMapper.createNewCart(newCart);
        }
        Long cartId = iCartMapper.getCartByUserId(userId);
        CartProduct cartProduct = CartProduct.builder()
                .cartId(cartId)
                .productId(cartProductDTO.getProductId())
                .quantity(cartProductDTO.getQuantity())
                .build();
        iCartMapper.addProductToCart(cartProduct);

    }

    public void createNewCar(CartDTO cartDTO) {

    }
}
// kiểm tra đã có cart chưa ?
// - có rồi thì thêm bản ghi vào cart_product
// - chưa có thì tạo cart mới, thêm bản ghi mới vào cart_product;
// ---> cần thêm bản ghi cart mới, thêm bản ghi cart_product mới