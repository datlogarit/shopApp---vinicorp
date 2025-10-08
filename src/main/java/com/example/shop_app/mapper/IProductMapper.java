package com.example.shop_app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shop_app.DTOs.product.ProductDetailViewDTO;
import com.example.shop_app.DTOs.product.ProductHomeViewDTO;

@Mapper
public interface IProductMapper {
    public List<ProductHomeViewDTO> getAllProduct();

    public ProductDetailViewDTO detailProduct(@Param("productId") Long productId);
}
