package com.example.shop_app.DTOs.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductToConfirm {
    private Long productId;
    private Integer quantity;
}
//gửi request lên server.server lấy dữ liệu này trả về tương ứng
// 1 list gồm productId và quantity là đc