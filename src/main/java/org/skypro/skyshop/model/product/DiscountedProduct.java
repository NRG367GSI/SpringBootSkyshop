package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discount;

    public DiscountedProduct(String productName, int basePrice, int discount) {
        super(productName);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("цена продукта должна быть строго больше 0. Ввели: " + basePrice);
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("процент должен быть числом в диапазоне от 0 до 100 включительно! Ввели: " + discount);
        }
        this.basePrice = basePrice;
        this.discount = discount;
    }

    @Override
    public int getPriceProduct() {
        return (int) basePrice * (1 - discount / 100);
    }

    @Override
    public String toString() {
        return productName + " : " + basePrice + " (" + discount + "%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
