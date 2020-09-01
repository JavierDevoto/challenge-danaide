package com.challenge.model;

import com.challenge.controller.request.CartItemRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ShoppingCart {

    protected Long id;
    protected List<CartItem> items = new ArrayList<>();
    protected String username;
    protected LocalDate creationDate;
    protected Boolean finished = false;

    @JsonIgnore
    private Optional<CartItem> getItemById(Long itemId) {
        return items
            .stream()
            .filter(item -> itemId.equals(item.getItem().getId()))
            .findFirst();
    }

    @JsonIgnore
    public Boolean hasItem(Long id) {
        return getItemById(id).isPresent();
    }

    @JsonIgnore
    public void addItem(CartItem cartItem) {
        items.add(cartItem);
    }

    @JsonIgnore
    public void removeItem(Long id) {
        if(getItemById(id).isPresent()) {
            items.remove(getItemById(id).get());
        }
    }

    @JsonIgnore
    public void addItemQuantity(CartItemRequest cartItemRequest) {
        Optional<CartItem> cartItemStored = getItemById(cartItemRequest.getItemId());
        CartItem cartItem = cartItemStored.get();
        cartItem.setQuantity(cartItem.getQuantity() + cartItemRequest.getQuantity());
    }

    @JsonIgnore
    public void removeItemQuantity(Long id, Integer quantity) {
        Optional<CartItem> cartItemStored = getItemById(id);
        CartItem cartItem = cartItemStored.get();
        cartItem.removeQuantity(quantity);
        if (cartItem.getQuantity() <= 0) {
            removeItem(id);
        }
    }

    @JsonIgnore
    public void removeAllItems() {
        items = new ArrayList<CartItem>();
    }

    @JsonIgnore
    public Integer getTotalProductsQuantity() {
        Integer products = 0;
        for (CartItem cartItem : items) {
            products += cartItem.getQuantity();
        }
        return products;
    }

    public Double getTotal() {
        Double total = 0.0;
        for (CartItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public Double getTotalWithDiscount() {
        return getTotal() - getDiscount();
    }

    @JsonIgnore
    public Double getDiscount() {
        Double discount = 0.0;
        discount += calculateFiveProductDiscount();
        discount += calculateTenProductDiscount();
        return discount;
    }

    @JsonIgnore
    protected Double calculateFiveProductDiscount() {
        return getTotalProductsQuantity() > 5 ? 0.1 * getTotal() : 0.0;
    }

    @JsonIgnore
    abstract Double calculateTenProductDiscount();

}
