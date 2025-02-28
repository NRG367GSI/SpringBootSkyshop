package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ShopController {

    StorageService storageService;

    // Внедрение StorageService через конструктор
    @Autowired
    public ShopController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/products")
    public Map<UUID, Product> getAllProducts() {
        return storageService.getAllProduct(); // Возвращаем Map<UUID, Product> в формате JSON
    }

    @GetMapping("/articles")
    public Map<UUID, Article> getAllArticles() {
        return storageService.getAllArticle(); // Возвращаем Map<UUID, Article> в формате JSON
    }
}
