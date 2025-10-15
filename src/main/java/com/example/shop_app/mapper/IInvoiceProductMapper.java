package com.example.shop_app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shop_app.domains.InvoiceProduct;
@Mapper
public interface IInvoiceProductMapper {
    public void createInvoiceProduct(@Param("invoiceProduct") InvoiceProduct invoiceProduct);
}
