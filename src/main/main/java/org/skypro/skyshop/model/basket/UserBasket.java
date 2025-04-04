package org.skypro.skyshop.model.basket;

import java.util.List;

public final class UserBasket {
    private final List<BasketItem> basketItems;
    private final int total;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        total = basketItems.stream().
                mapToInt(product -> product.getCountProduct() * product.getProduct().getPriceProduct())
                .reduce(0, Integer::sum);
    }
}
