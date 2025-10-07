package com.example.shop_app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shop_app.DTOs.responses.DetailProductViewDTO;
import com.example.shop_app.domains.Product;

@Mapper
public interface IProductMapper {
    public List<Product> getAllProduct();

    public DetailProductViewDTO detailProduct(@Param("productId") Long productId);
}
