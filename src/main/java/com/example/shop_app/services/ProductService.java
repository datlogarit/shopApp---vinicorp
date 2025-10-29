package com.example.shop_app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.product.ProductDTO;
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
        // Product ---> ProductHomeViewDTO
        List<Product> newProduct = iProductMapper.getAllProduct();
        List<ProductHomeViewDTO> lProductHomeViewDTO = new ArrayList<ProductHomeViewDTO>();
        for (Product product : newProduct) {
            ProductHomeViewDTO productHomeViewDTO = ProductHomeViewDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .display_avt(product.getDisplayAvt())
                    .build();
            lProductHomeViewDTO.add(productHomeViewDTO);
        }
        return lProductHomeViewDTO;
    }

    // Because have to join 2 table so return riderect view;
    public ProductDetailViewDTO getDetailProduct(Long productId) {
        return iProductMapper.detailProduct(productId);
    }

    public List<ProductHomeViewDTO> getProductByName(String keyword){
         List<Product> listP = iProductMapper.getProductByName(keyword);
         List<ProductHomeViewDTO> lProductHomeViewDTO = new ArrayList<ProductHomeViewDTO>();
        for (Product product : listP) {
            ProductHomeViewDTO productHomeViewDTO = ProductHomeViewDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .display_avt(product.getDisplayAvt())
                    .build();
            lProductHomeViewDTO.add(productHomeViewDTO);
        }
         return lProductHomeViewDTO;
    }

    public void updateQuantityProduct(ProductDTO productDTO){
        Product existProduct = Product.builder()
        .id(productDTO.getId())
        .numAvailable(productDTO.getNumAvailable())
        .build();
        iProductMapper.updateProduct(existProduct);
    }
}
