package com.example.shop_app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.order.ConfirmOrderViewDTO;
import com.example.shop_app.DTOs.product.ProductNumberDTO;
import com.example.shop_app.domains.Product;
import com.example.shop_app.mapper.IProductMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final IProductMapper iProductMapper;

    public List<ConfirmOrderViewDTO> getDataOrderView(List<ProductNumberDTO> listProductConfrirm){
        List<ConfirmOrderViewDTO> orderConfirmViewDTOs = new ArrayList<>();
        for (ProductNumberDTO productToConfirm : listProductConfrirm) {
            // productToConfirm --> OrderConfirmViewDTO
            Product product = iProductMapper.getProductById(productToConfirm.getProductId());
            ConfirmOrderViewDTO orderConfirmViewDTO = ConfirmOrderViewDTO.builder()
            //load from security context when login;
            .fullName("Doan Minh Dat")
            .address("Hai Chinh - Hai Hau - Nam Dinh")
            .phoneNumber("0817894581")

            .imgUrl(product.getDisplay_avt())
            .price(product.getPrice())
            .productName(product.getName())
            .quantity(productToConfirm.getQuantity())
            .build();
            orderConfirmViewDTOs.add(orderConfirmViewDTO);
        }
        return orderConfirmViewDTOs;
    }

    public Long caculateTotal(List<ProductNumberDTO> productNumberDTO){
        Long totalCost = 0L;
        for (ProductNumberDTO pNumberDTO : productNumberDTO) {
            Product existProduct = iProductMapper.getProductById(pNumberDTO.getProductId());
            Long costPerProduct = existProduct.getPrice() *  pNumberDTO.getQuantity();
            totalCost += costPerProduct;
        }
        return totalCost;
    }
}
