package com.example.shop_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop_app.domains.Product;
import com.example.shop_app.mapper.IProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final IProductMapper iProductMapper;

    public List<Product> getAllProducts(){
        return iProductMapper.getAllProduct();
    }
}
