package com.example.shop_app.DTOs.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// nhiệm vụ là: lấy ra thông tin của cart(ảnh, tên, số lượng) từ userId tương ứng
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDTO {
    private Long userId;
    private Long cartId;
    private Long productId;
    private String productName;
    private Long price;
    private String displayAvt;
    private Integer quantity;
}
