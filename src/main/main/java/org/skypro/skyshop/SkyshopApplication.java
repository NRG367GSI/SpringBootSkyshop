package org.skypro.skyshop;

import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.UUID;

@SpringBootApplication
public class SkyshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyshopApplication.class, args);
	}
}
