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
        if (this.storageService.getProductById(id).isPresent()) {
            this.productBasket.addProduct(id);
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " not found.");
        }
    }

    public List<BasketItem> getUserBasket() {
        // Получаем все продукты из корзины
        List<Product> productsInBasket = storageService.getAllProduct().values().stream().toList();
        List<BasketItem> basketItemse = productBasket.getAllProduct()
                .entrySet()
                .stream()
                .map(entry -> new BasketItem(storageService.getProductById(entry.getKey()).orElseThrow(), entry.getValue()))
                .toList();
        return basketItemse;
    }
}
