package com.example.shop_app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shop_app.DTOs.product.ProductDetailViewDTO;
import com.example.shop_app.domains.Product;

@Mapper
public interface IProductMapper {
    public List<Product> getAllProduct();
    public Product getProductById(@Param("productId") Long producId);
    // need join with ProductImage table
    public ProductDetailViewDTO detailProduct(@Param("productId") Long productId);
    public List<Product> getProductByName(@Param("keyword") String keyword);
    public void updateProduct(@Param("product") Product product);
}
