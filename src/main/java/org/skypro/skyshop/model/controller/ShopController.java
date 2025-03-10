package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ShopController {

    private final SearchService searchService;
    private final ProductBasket productBasket;

    @Autowired
    public ShopController(SearchService searchService, ProductBasket productBasket) {
        this.searchService = searchService;
        this.productBasket = productBasket;
    }

    @GetMapping("/products")
    public Map<UUID, Product> getAllProducts() {
        return searchService.getProducts();
    }

    @GetMapping("/articles")
    public Map<UUID, Article> getAllArticles() {
        return searchService.getArticles();
    }

    @GetMapping("/search")
    public List<Searchable> search(@RequestParam String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        this.productBasket.addProduct(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return getUserBasket();
    }
}
