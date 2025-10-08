package com.example.shop_app.mapper;

import java.util.List;

import com.example.shop_app.DTOs.cart.CartProductViewDTO;
import com.example.shop_app.domains.Cart;
import com.example.shop_app.domains.CartProduct;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ICartMapper {
    public List<CartProductViewDTO> getInfoCartByUserId(@Param("userId") Long userId);

    public Long existCartByUserId(@Param("userId") Long userId);

    public void createNewCart(@Param("cart") Cart cart);

    public Long getCartByUserId(@Param("userId") Long userId);

    public void addProductToCart(@Param("cartProduct") CartProduct cartProduct);
}