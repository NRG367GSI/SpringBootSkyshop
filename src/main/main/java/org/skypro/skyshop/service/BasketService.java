package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    @Autowired
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        // Если продукт не найден, исключение выбросится здесь
        this.storageService.getProductById(id);

        // Если продукт найден, добавляем его в корзину
        this.productBasket.addProduct(id);
    }

    public List<BasketItem> getUserBasket() {
        List<BasketItem> basketItemse = productBasket.getAllProduct()
                .entrySet()
                .stream()
                .map(entry -> new BasketItem(storageService.getProductById(entry.getKey()), entry.getValue()))
                .toList();
        return basketItemse;
    }
}
