package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

import java.util.Optional;

public final class BasketItem {
    private final Product product;
    private final int countProduct;

    public BasketItem(Product product, int countProduct) {
        this.product = product;
        this.countProduct = countProduct;
    }

    public Product getProduct() {
        return product;
    }

    public int getCountProduct() {
        return countProduct;
    }
}
