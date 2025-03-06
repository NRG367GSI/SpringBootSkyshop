package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductBasket {
    private final Map<UUID, Integer> basket;

    public ProductBasket() {
        this.basket = new HashMap<UUID, Integer>();
    }

    public void addProduct(UUID id) {
        basket.put(id, basket.getOrDefault(id, 0) + 1);
    }

    public Map<UUID, Integer> getAllProduct() {
        Map<UUID, Integer> unmodifiableMap = Collections.unmodifiableMap(basket);
        return unmodifiableMap;
    }

}