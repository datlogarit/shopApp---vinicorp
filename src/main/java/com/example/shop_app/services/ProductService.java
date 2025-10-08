package com.example.shop_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.product.ProductDetailViewDTO;
import com.example.shop_app.DTOs.product.ProductHomeViewDTO;
import com.example.shop_app.mapper.IProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final IProductMapper iProductMapper;

    public List<ProductHomeViewDTO> getAllProducts() {
        return iProductMapper.getAllProduct();
    }

    public ProductDetailViewDTO getDetailProduct(Long productId) {
        return iProductMapper.detailProduct(productId);
    }
}
