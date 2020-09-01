package com.challenge.dbobjects;

import com.challenge.model.CartItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemDbMapper {

    public CartItem mappObject(CartItemDB cartItemDB) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDB.getId());
        cartItem.setItem(cartItemDB.getItem());
        cartItem.setQuantity(cartItemDB.getQuantity());
        return cartItem;
    }

    public CartItemDB mappObject(CartItem cartItem) {
        CartItemDB cartItemDB = new CartItemDB();
        cartItemDB.setId(cartItem.getId());
        cartItemDB.setItem(cartItem.getItem());
        cartItemDB.setQuantity(cartItem.getQuantity());
        return cartItemDB;
    }

    public List<CartItem> mappObjects(List<CartItemDB> cartItemsDB) {
        List<CartItem> cartItems = new ArrayList<>();
        for (CartItemDB itemsDb : cartItemsDB) {
            cartItems.add(mappObject(itemsDb));
        }
        return cartItems;
    }

}
