package com.example.shop_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class ShopAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShopAppApplication.class, args);
	}
}
