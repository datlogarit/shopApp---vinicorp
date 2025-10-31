package com.example.shop_app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.order.ConfirmOrderViewDTO;
import com.example.shop_app.DTOs.product.ProductNumberDTO;
import com.example.shop_app.domains.Product;
import com.example.shop_app.domains.Users;
import com.example.shop_app.mapper.IProductMapper;
import lombok.RequiredArgsConstructor;

/**
 * service handle function related order
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final IProductMapper iProductMapper;

    /**
     * get data for order view
     * @param listProductConfrirm - list product is send by client
     * @return info needed for confirm order page
     */
    public List<ConfirmOrderViewDTO> getDataOrderView(List<ProductNumberDTO> listProductConfrirm){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof Users)) {
            throw new RuntimeException("User not authenticated!");
        }
        Users user = (Users) principal;

        List<ConfirmOrderViewDTO> orderConfirmViewDTOs = new ArrayList<>();
        for (ProductNumberDTO productToConfirm : listProductConfrirm) {
            // productToConfirm --> OrderConfirmViewDTO
            Product product = iProductMapper.getProductById(productToConfirm.getProductId());
            ConfirmOrderViewDTO orderConfirmViewDTO = ConfirmOrderViewDTO.builder()
            //load from security context when login;
            .fullName(user.getFullName())
            .address(user.getAddress())
            .phoneNumber(user.getPhoneNumber())

            .imgUrl(product.getDisplayAvt())
            .price(product.getPrice())
            .productName(product.getName())
            .quantity(productToConfirm.getQuantity())
            .build();
            orderConfirmViewDTOs.add(orderConfirmViewDTO);
        }
        return orderConfirmViewDTOs;
    }

    /**
     * caculate total money of order
     * @param productNumberDTO number of specific product
     * @return total cost
     */
    public Long caculateTotal(List<ProductNumberDTO> productNumberDTO){
        Long totalCost = 0L;
        for (ProductNumberDTO pNumberDTO : productNumberDTO) {
            Product existProduct = iProductMapper.getProductById(pNumberDTO.getProductId());
            Long costPerProduct = existProduct.getPrice() *  pNumberDTO.getQuantity();
            totalCost += costPerProduct;
        }
        return totalCost;
    }

    public void checkQuantityInput(List<ProductNumberDTO> productToConfirm){
        for (ProductNumberDTO productNumberDTO : productToConfirm) {
            Product existProduct = iProductMapper.getProductById(productNumberDTO.getProductId());
            if (productNumberDTO.getQuantity() < 1) {
                throw new RuntimeException("The number of product must be greate than 0");
            }
            if (productNumberDTO.getQuantity() > existProduct.getNumAvailable()) {
                throw new RuntimeException("The number of product "+ existProduct.getName() +" exceed the stock. (Max: "+ existProduct.getNumAvailable()+"). Please check again");
            }
        }
    }
}



