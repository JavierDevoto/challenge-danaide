package com.challenge.service;

import com.challenge.model.CartItem;
import com.challenge.repository.memory.CartItemRepositoryMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepositoryMem cartItemRepository;

	@Override
	public CartItem createCartItem(CartItem cartItem) {
		return cartItemRepository.createItem(cartItem);
	}

	@Override
	public CartItem updateCartItem(CartItem cartItem) {
		return cartItemRepository.updateItem(cartItem);
	}

	@Override
	public List<CartItem> getAllCartItems() {
		return cartItemRepository.getAllItems();
	}

	@Override
	public CartItem getCartItemById(Long cartItemId) {
		return cartItemRepository.getItemByPk(cartItemId);
	}

	@Override
	public void deleteCartItem(Long cartItemId) {
	}

}
