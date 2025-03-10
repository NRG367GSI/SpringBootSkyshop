package org.skypro.skyshop.model.basket;

import java.util.LinkedList;
import java.util.List;

public final class UserBasket {
    private final List<BasketItem> basketItems;
    private final int total;


    // у нас есть ProductBasket хронящий Map<id, countProduct>
    // BasketItem хронящий Product и колличество продукта count
    // сам продукт имеет в себе цену
    // нам нужно что б конструктор получал список
    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        total = basketItems.stream().
                mapToInt(product -> product.getCountProduct() * product.getProduct().getPriceProduct())
                .reduce(0, Integer::sum);
    }
}
