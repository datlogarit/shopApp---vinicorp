package com.example.shop_app.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_app.DTOs.requests.CartToOrderDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    @PostMapping("")
    @ResponseBody
    private ResponseEntity<?> postCart(@RequestBody CartToOrderDTO cartToOrderDTO, HttpSession session) throws Exception{
        session.setAttribute("result", cartToOrderDTO);
        return ResponseEntity.ok("bind to server successully");
    }

    @GetMapping("")
    private String oderPage(Model model, HttpSession session) {
        //gửi ngượi lại cho client
        model.addAttribute("items", session.getAttribute("result"));
        return "order";
    }   



    //server nhận đc và lưu vào db mới
    //client lại phải request đến endpoint mới và gọi đến controller để xử lý đống dữ liệu vừa rồi
}
