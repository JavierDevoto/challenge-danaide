package com.challenge.service;

import com.challenge.model.CartItem;

import java.util.List;

public interface CartItemService {

	CartItem createCartItem(CartItem cartItem);

	CartItem updateCartItem(CartItem cartItem);

	List<CartItem> getAllCartItems();

	CartItem getCartItemById(Long cartItemId);

	void deleteCartItem(Long cartItemId);

}
