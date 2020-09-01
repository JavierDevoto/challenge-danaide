package com.challenge.repository.memory;

import com.challenge.model.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ShoppingCartRepositoryMem {

    private Long id = 0l;

    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    private Long getNextId() {
        id += 1;
        return id;
    }

    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCarts;
    }

    public Optional<ShoppingCart> getShoppingCartByPk(Long id) {
        return shoppingCarts
                .stream()
                .filter(shoppingCart -> id.equals(shoppingCart.getId()))
                .findFirst();
    }

    public Optional<ShoppingCart> getActiveShoppingCartByPk(Long id) {
        return shoppingCarts
            .stream()
            .filter(shoppingCart -> id.equals(shoppingCart.getId()) && !shoppingCart.getFinished())
            .findFirst();
    }

    public Optional<ShoppingCart> getUserActiveShoppingCart(String username) {
        return shoppingCarts
            .stream()
            .filter(shoppingCart -> username.equals(shoppingCart.getUsername()) && !shoppingCart.getFinished())
            .findFirst();
    }

    public List<ShoppingCart> getUserShoppingCarts(String username) {
        return shoppingCarts
            .stream()
            .filter(shoppingCart -> username.equals(shoppingCart.getUsername()))
            .collect(Collectors.toList());
    }

    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setId(getNextId());
        shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
        Optional<ShoppingCart> shoppingCartDb = getShoppingCartByPk(shoppingCart.getId());
        if(shoppingCartDb.isPresent()) {
            ShoppingCart shoppingCartToUpdate = shoppingCartDb.get();
            shoppingCartToUpdate.setItems(shoppingCart.getItems());
            shoppingCartToUpdate.setFinished(shoppingCart.getFinished());
            return shoppingCartToUpdate;
        } else {
            return null;
        }
    }

    public List<ShoppingCart> getUnfinishedShoppingCarts() {
        return shoppingCarts
            .stream()
            .filter(shoppingCart -> !shoppingCart.getFinished())
            .collect(Collectors.toList());
    }

    public void deleteShoppingCart(ShoppingCart shoppingCart) {
        shoppingCarts.remove(shoppingCart);
    }

    public Double getTotalForMonth(LocalDate date) {
        List<ShoppingCart> shoppingCartsMonth = shoppingCarts.stream()
            .filter(shoppingCart -> shoppingCart.getFinished() && date.compareTo(shoppingCart.getCreationDate()) <= 0)
            .collect(Collectors.toList());
        Double total = 0d;
        for (ShoppingCart shopp: shoppingCartsMonth) {
            total += shopp.getTotalWithDiscount();
        }
        return total;
    }

    public Integer getNumberCartsByDateForUser(String username, LocalDate date) {
        return  shoppingCarts
            .stream()
            .filter(shoppingCart -> username.equals(shoppingCart.getUsername()) &&
                                    date.compareTo(shoppingCart.getCreationDate()) <= 0)
            .collect(Collectors.toList())
            .size();
    }

}
