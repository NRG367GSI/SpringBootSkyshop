package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ShopController {

    private final SearchService searchService;
    private final BasketService basketService;

    @Autowired
    public ShopController(SearchService searchService, BasketService basketService) {
        this.searchService = searchService;
        this.basketService = basketService;
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

    @GetMapping("/shop/basket/add/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        this.basketService.addProductToBasket(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/shop/basket")
    public List<BasketItem> getUserBasket() {
        return basketService.getUserBasket();
    }
}
