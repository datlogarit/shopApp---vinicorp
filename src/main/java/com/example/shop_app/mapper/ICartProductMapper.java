package com.example.shop_app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shop_app.DTOs.cart.CartProductViewDTO;
import com.example.shop_app.domains.CartProduct;

@Mapper
public interface ICartProductMapper {
    public List<CartProductViewDTO> getInfoCartByUserId(@Param("userId") Long userId);

    public void addProductToCart(@Param("cartProduct") CartProduct cartProduct);

    public void deleteProductToCart(@Param("cartId") Long cartId, @Param("productId") Long productId);

    public Long checkExistByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);

    public void updateCartProduct(@Param("cartId") Long cartId,
            @Param("productId") Long productId,
            @Param("cartProduct") CartProduct cartProduct);

    public CartProduct getByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);
}