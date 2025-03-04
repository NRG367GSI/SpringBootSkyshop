package org.skypro.skyshop.model.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    protected final String productName;
    private final UUID id;

    public Product(UUID id, String productName) {
        this.id = id;
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

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return productName;
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public UUID getID() {
        return id;
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
