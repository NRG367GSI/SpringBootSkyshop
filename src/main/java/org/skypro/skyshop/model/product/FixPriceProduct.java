package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product{

    private static final int PRICE_PRODUCT = 100;

    public FixPriceProduct(String productName, UUID id) {
        super(productName, id);
    }

    @Override
    public int getPriceProduct() {
        return PRICE_PRODUCT;
    }

    @Override
    public String toString() {
        return productName + " : " + PRICE_PRODUCT;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public UUID getID() {
        return null;
    }
}
