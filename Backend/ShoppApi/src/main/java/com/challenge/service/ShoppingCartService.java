package com.challenge.service;

import com.challenge.controller.request.CartItemRequest;
import com.challenge.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

	List<ShoppingCart> getAllShoppingCart();

	ShoppingCart getActiveShoppingCartById(Long shoppingCartId);

	ShoppingCart getActiveShoppingCartByUser(String username);

	ShoppingCart createShoppingCartForUser(String username);

	ShoppingCart addItem(Long id, CartItemRequest cartItemRequest);

	ShoppingCart removeItemQuantity(Long id, CartItemRequest cartItemRequest);

	ShoppingCart removeItem(Long shoppingCartId, Long itemId);

	ShoppingCart finishShoppingCart(Long id);

	ShoppingCart emptyShoppingCart(Long id);

	void cleanOldShoppingCarts();

	void deleteShoppingCart(Long id);

	Boolean checkVipStatusForUser(String username);

}
