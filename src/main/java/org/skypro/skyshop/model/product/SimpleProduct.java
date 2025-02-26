package org.skypro.skyshop.product;

public class SimpleProduct extends Product{
    private final int priceProduct;

    public SimpleProduct(String productName, int priceProduct) {
        super(productName);
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
