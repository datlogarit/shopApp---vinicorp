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

/**
 * service handle related domain product
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final IProductMapper iProductMapper;

    /**
     * get all product to display on view
     * @return list product information needed for home page
     */
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

    /**
     * get info in detail page of product
     * @param productId 
     * @return infor needed for detail pages
     */
    public ProductDetailViewDTO getDetailProduct(Long productId) {
        return iProductMapper.detailProduct(productId);
    }

    /**
     * get product by name
     * @param keyword for search
     * @return list infor needed for home page
     */
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

    /**
     * update the quantity in the stock of product
     * @param productDTO - data needed for add, update product
     */
    public void updateQuantityProduct(ProductDTO productDTO){
        Product existProduct = Product.builder()
        .id(productDTO.getId())
        .numAvailable(productDTO.getNumAvailable())
        .build();
        iProductMapper.updateProduct(existProduct);
    }
}
