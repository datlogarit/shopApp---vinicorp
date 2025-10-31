package com.example.shop_app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shop_app.DTOs.invoice.ListInvoiceMapping;
import com.example.shop_app.DTOs.invoice.ListInvoiceView;
import com.example.shop_app.domains.Invoice;

@Mapper
public interface IInvoiceMapper {
    /**
     * create a new invoice
     * @param invoice (domain)
     */
    public void createInvoice(@Param("invoice") Invoice invoice);

    public List<ListInvoiceView> getAllInvoiceByUserId(@Param("userId") Long userId);

    /**
     * check permition of user
     * @param userId 
     * @param invoiceId
     * @return the number that represents the existence of the record (1 - record exists, null - record does not exist)
     */
    public Long checkExistByInvoiceIdAndUserId(@Param("userId") Long userId, @Param("invoiceId") Long invoiceId);

    /**
     * get informatino in invoice
     * @param invoiceId
     * @return information is derict mapped from db 
     */
    public List<ListInvoiceMapping> getInfoExportInvoice(@Param("invoiceId") Long invoiceId);
}
