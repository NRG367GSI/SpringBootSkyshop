package org.skypro.skyshop.product;


import org.skypro.skyshop.content.Searchable;

import java.util.Objects;

public abstract class Product implements Searchable {
    protected final String productName;

    public Product(String productName) {
        if (productName == null) {
            throw new IllegalArgumentException("Название продукта не может быть пустым. Вы ввели: ");
        }
        if (productName.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть состоять только из пробелов. Вы ввели: ");
        }
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public abstract int getPriceProduct();

    //Метод isSpecial, возвращает true если товар специальный и false, если товар обычный
    //Специальный товар — это товар со скидкой или фиксированной стоимостью
    public abstract boolean isSpecial();

    @Override
    public String getSearchTerm() {
        return productName;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }
}
