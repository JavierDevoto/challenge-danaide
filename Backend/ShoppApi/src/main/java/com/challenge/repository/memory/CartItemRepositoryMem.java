package com.challenge.repository.memory;

import com.challenge.model.CartItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CartItemRepositoryMem {

    private Long id = 0l;

    private List<CartItem> cartItems = new ArrayList<>();

    private Long getNextId() {
        id += 1;
        return id;
    }

    public List<CartItem> getAllItems() {
        return cartItems;
    }

    public CartItem getItemByPk(Long id) {
        return cartItems
            .stream()
            .filter(cartItem -> id.equals(cartItem.getId()))
            .collect(Collectors.toList())
            .get(0);
    }

    public CartItem createItem(CartItem cartItem) {
        cartItem.setId(getNextId());
        cartItems.add(cartItem);
        return cartItem;
    }

    public CartItem updateItem(CartItem cartItem) {
        CartItem cartItem1 = getItemByPk(cartItem.getId());
        cartItem1.setQuantity(cartItem.getQuantity());
        cartItem1.setItem(cartItem.getItem());
        return cartItem1;
    }

}
