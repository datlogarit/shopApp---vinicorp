package com.example.shop_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.cart.CartProductViewDTO;
import com.example.shop_app.DTOs.product.ProductNumberDTO;
import com.example.shop_app.domains.Cart;
import com.example.shop_app.domains.CartProduct;
import com.example.shop_app.domains.Product;
import com.example.shop_app.mapper.ICartMapper;
import com.example.shop_app.mapper.ICartProductMapper;
import com.example.shop_app.mapper.IProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartProductService {
    private final ICartProductMapper iCartProductMapper;
    private final ICartMapper iCartMapper;
    private final IProductMapper iProductMapper;

    public List<CartProductViewDTO> getProductInCart(Long userId) {
        return iCartProductMapper.getInfoCartByUserId(userId);
    }

    public void addProductToCart(Long userId, ProductNumberDTO productNumberDTO) {
        // check stock
        Product product = iProductMapper.getProductById(productNumberDTO.getProductId());
        if (productNumberDTO.getQuantity() > product.getNumAvailable()) {
            throw new RuntimeException("Order exceeds remaining quantity");
        }
        // create cart if cart not exist
        if (iCartMapper.existCartByUserId(userId) == null) {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            iCartMapper.createNewCart(newCart);
        }

        Long cartId = iCartMapper.getCartByUserId(userId);
        if (iCartProductMapper.checkExistByCartIdAndProductId(cartId, productNumberDTO.getProductId()) != null) {
            // if product have aready in cart then update quantity
            CartProduct oldCartProduct = iCartProductMapper.getByCartIdAndProductId(cartId,
                    productNumberDTO.getProductId());
            Integer updatedQuantity = productNumberDTO.getQuantity() + oldCartProduct.getQuantity();
            if (updatedQuantity > product.getNumAvailable()) {
                updatedQuantity = product.getNumAvailable();
                CartProduct newCartProduct = CartProduct.builder()
                        .quantity(updatedQuantity)
                        .build();
                updateCartProduct(cartId, productNumberDTO.getProductId(), newCartProduct);
                throw new RuntimeException("Currently, the quantity of products in your shopping cart is at the maximum in stock.");
            }
            CartProduct newCartProduct = CartProduct.builder()
                    .quantity(updatedQuantity)
                    .build();
            updateCartProduct(cartId, productNumberDTO.getProductId(), newCartProduct);
        } else {// create new product in cart
            CartProduct cartProduct = CartProduct.builder()
                    .cartId(cartId)
                    .productId(productNumberDTO.getProductId())
                    .quantity(productNumberDTO.getQuantity())
                    .build();
            iCartProductMapper.addProductToCart(cartProduct);
        }
    }

    // update product in the cart
    public void updateCartProduct(Long cartId, Long productId, CartProduct cartProduct) {
        iCartProductMapper.updateCartProduct(cartId, productId, cartProduct);
    }

    public void deleteProductToCart(Long userId, Long productId) {
        // tìm cartId theo userId
        Long cartId = iCartMapper.getCartByUserId(userId);
        iCartProductMapper.deleteProductToCart(cartId, productId);
    }

}
