package com.example.shop_app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shop_app.DTOs.invoice.ListInvoiceMapping;
import com.example.shop_app.DTOs.invoice.ListInvoiceView;
import com.example.shop_app.domains.Invoice;

@Mapper
public interface IInvoiceMapper {
    public void createInvoice(@Param("invoice") Invoice invoice);

    public List<ListInvoiceView> getAllInvoiceByUserId(@Param("userId") Long userId);

    public List<ListInvoiceMapping> getInfoExportInvoice(@Param("orderId") Long orderId);
}
