package com.challenge.model;

public class ShoppingCartPromotional extends ShoppingCart {

    @Override
    Double calculateTenProductDiscount() {
        return getTotalProductsQuantity() > 10 ? 500 : 0.0;
    }

}
