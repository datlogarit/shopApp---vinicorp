package com.example.shop_app.services;

import org.springframework.stereotype.Service;

import com.example.shop_app.DTOs.invoice.InvoiceDTO;
import com.example.shop_app.domains.Invoice;
import com.example.shop_app.mapper.IInvoiceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final IInvoiceMapper iInvoiceMapper;

    public void createInvoice(InvoiceDTO invoiceDTO) {
        Invoice newInvoice = Invoice.builder()
                // should be auto get from backend
                .customerId(invoiceDTO.getCustomerId())
                .payMethod(invoiceDTO.getPayMethod())
                .orderStatus("ordered")
                .build();
        iInvoiceMapper.createInvoice(newInvoice);
    }
}
