package com.example.shop_app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.product.ProductDetailViewDTO;
import com.example.shop_app.DTOs.product.ProductHomeViewDTO;
import com.example.shop_app.domains.Product;
import com.example.shop_app.mapper.IProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final IProductMapper iProductMapper;

    public List<ProductHomeViewDTO> getAllProducts() {
        List<Product> newProduct = iProductMapper.getAllProduct();
        List<ProductHomeViewDTO> lProductHomeViewDTO = new ArrayList<ProductHomeViewDTO>();
        for (Product product : newProduct) {
            ProductHomeViewDTO productHomeViewDTO = ProductHomeViewDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .display_avt(product.getDisplay_avt())
                    .build();
            lProductHomeViewDTO.add(productHomeViewDTO);
        }
        return lProductHomeViewDTO;
    }

    public ProductDetailViewDTO getDetailProduct(Long productId) {
        return iProductMapper.detailProduct(productId);
    }
}
