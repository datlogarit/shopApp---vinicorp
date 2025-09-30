package com.example.shop_app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.shop_app.domains.Product;

@Mapper
public interface IProductMapper {
    public List<Product> getAllProduct();
}
