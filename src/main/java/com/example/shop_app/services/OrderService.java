package com.example.shop_app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.cart.ProductToConfirm;
import com.example.shop_app.DTOs.orderConfirm.OrderConfirmViewDTO;
import com.example.shop_app.domains.Product;
import com.example.shop_app.mapper.IOrderMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final IOrderMapper iOrderMapper;

    public List<OrderConfirmViewDTO> getDataOrderView(List<ProductToConfirm> listProductConfrirm){
        List<OrderConfirmViewDTO> orderConfirmViewDTOs = new ArrayList<>();
        for (ProductToConfirm productToConfirm : listProductConfrirm) {
            // productToConfirm --> OrderConfirmViewDTO
            Product product = iOrderMapper.getProductOfOrderPage(productToConfirm.getProductId());
            OrderConfirmViewDTO orderConfirmViewDTO = OrderConfirmViewDTO.builder()
            .imgUrl(product.getDisplay_avt())
            .price(product.getPrice())
            .productName(product.getName())
            .quantity(productToConfirm.getQuantity())
            .build();
            orderConfirmViewDTOs.add(orderConfirmViewDTO);
        }
        return orderConfirmViewDTOs;
    }
}
