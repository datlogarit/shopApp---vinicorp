package com.example.shop_app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shop_app.domains.Product;

@Mapper
public interface IOrderMapper {
    public Product getProductOfOrderPage(@Param("productId") Long productId);
}
