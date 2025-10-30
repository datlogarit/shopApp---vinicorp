package com.example.shop_app.mapper;

import com.example.shop_app.domains.Cart;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ICartMapper {
    /**
     * check existion of cart by userId
     * @param userId
     * @return
     */
    public Long existCartByUserId(@Param("userId") Long userId);

    /**
     * create new cart
     * @param cart
     */
    public void createNewCart(@Param("cart") Cart cart);

    /**
     * get cartInfo by userId
     * @param userId
     * @return
     */
    public Long getCartByUserId(@Param("userId") Long userId);

}