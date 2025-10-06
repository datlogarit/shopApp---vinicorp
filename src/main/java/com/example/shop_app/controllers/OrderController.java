package com.example.shop_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.shop_app.DTOs.requests.ToOrderDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    @PostMapping("")
    @ResponseBody
    private ResponseEntity<?> cartToOrder(@RequestBody ToOrderDTO cartToOrderDTO, HttpSession session) throws Exception {
        session.setAttribute("result", cartToOrderDTO);
        return ResponseEntity.ok("Bind to server successfully");
    }

    @GetMapping("")
    private String getOrderPage(Model model, HttpSession session) {
        // gửi ngượi lại cho client
        model.addAttribute("items", session.getAttribute("result"));
        return "order";
    }

    // server nhận đc và lưu vào db mới
    // client lại phải request đến endpoint mới và gọi đến controller để xử lý đống
    // dữ liệu vừa rồi
}
