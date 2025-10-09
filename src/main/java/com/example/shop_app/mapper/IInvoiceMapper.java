package com.example.shop_app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shop_app.domains.Invoice;


@Mapper
public interface IInvoiceMapper {
    public void createInvoice(@Param("invoice") Invoice invoice);
}
