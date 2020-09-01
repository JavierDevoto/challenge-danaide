package com.challenge.model;

public class ShoppingCartRegular extends ShoppingCart{

    @Override
    Double calculateTenProductDiscount() {
        return getTotalProductsQuantity() > 10 ? 200 : 0.0;
    }

}
