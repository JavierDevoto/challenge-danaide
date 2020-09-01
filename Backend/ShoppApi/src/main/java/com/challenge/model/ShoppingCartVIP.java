package com.challenge.model;

import java.util.Comparator;

public class ShoppingCartVIP extends ShoppingCart{

    private Double getPriceLowestPricedItem() {
        return items
            .stream()
            .min(Comparator.comparing(item -> item.getItem().getPrice()))
            .get()
            .getItem()
            .getPrice();
    }

    @Override
    Double calculateTenProductDiscount() {
        if(getTotalProductsQuantity() > 10) {
            return getPriceLowestPricedItem() + 700;
        } else {
            return 0.0;
        }
    }

}
