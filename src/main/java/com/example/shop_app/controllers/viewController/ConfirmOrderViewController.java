package com.example.shop_app.controllers.viewController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.shop_app.DTOs.BaseAPIRespone;
import com.example.shop_app.DTOs.order.ConfirmOrderViewDTO;
import com.example.shop_app.DTOs.product.ProductNumberDTO;
import com.example.shop_app.domains.Users;
import com.example.shop_app.services.OrderService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * handle funtion related confirm order page
 */
@Controller
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class ConfirmOrderViewController {
    private final OrderService orderService;

    /**
     * receive product information ordered from customer
     * @param productToConfirm - list product information (productId, quantity) 
     * @param session - where data product is stored
     * @return handle message
     */
    @PostMapping("")
    @ResponseBody
    private ResponseEntity<?> bindToOrderConfirm(
            @Valid @RequestBody List<ProductNumberDTO> productToConfirm,
            HttpSession session) {
        orderService.checkQuantityInput(productToConfirm); // validate
        session.setAttribute("result", productToConfirm);
        return ResponseEntity.ok(new BaseAPIRespone<>(200, "success", "Bind to server successfully"));
    }

    /**
     * request confirm order page
     * @param model - where bind data to UI
     * @param session - where contain the data
     * @param userDetails - user object authenticated
     * @return order confirm page
     */
    @GetMapping("confirm")
    @SuppressWarnings("unchecked")
    private String getOrderPage(Model model, HttpSession session, @AuthenticationPrincipal Users userDetails) {
        List<ProductNumberDTO> listProduct = (List<ProductNumberDTO>) session.getAttribute("result");
        // ProductNumberDTO to OrderConfirmViewDTO (view)
        List<ConfirmOrderViewDTO> orderConfirmViewDTOs = orderService.getDataOrderView(listProduct);
        model.addAttribute("rawItems", listProduct);
        model.addAttribute("userId", userDetails.getId());
        model.addAttribute("totalPrice", orderService.caculateTotal(listProduct));
        model.addAttribute("items", orderConfirmViewDTOs);
        return "order-confirm";
    }
}