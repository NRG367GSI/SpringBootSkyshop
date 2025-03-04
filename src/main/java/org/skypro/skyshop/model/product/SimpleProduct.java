package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product{
    private final int priceProduct;

    public SimpleProduct(UUID id, String productName, int priceProduct) {
        super(id, productName);
        if (priceProduct <= 0) {
            throw new IllegalArgumentException("Цена продукта должна быть строго больше 0. Получено: " + priceProduct);
        }
        this.priceProduct = priceProduct;
    }

    @Override
    public int getPriceProduct() {
        return this.priceProduct;
    }

    @Override
    public String toString() {
        return productName + " : " + priceProduct;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
