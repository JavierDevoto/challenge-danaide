package com.challenge.service;

import com.challenge.controller.request.CartItemRequest;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.model.*;
import com.challenge.repository.memory.ShoppingCartRepositoryMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartRepositoryMem shoppingCartRepository;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private ItemServiceImpl itemService;

	@Autowired
	private CartItemServiceImpl cartItemService;

	@Autowired
	private DateServiceImpl dateService;

	@Override
	public void deleteShoppingCart(Long shoppingCartId) {
		Optional<ShoppingCart> shoppingCartDb = shoppingCartRepository.getActiveShoppingCartByPk(shoppingCartId);
		if(shoppingCartDb.isPresent()) {
			shoppingCartRepository.deleteShoppingCart(shoppingCartDb.get());
		} else {
			throw new ResourceNotFoundException("El carrito con id: " + shoppingCartId + " no existe o ya fue finalizado");
		}
	}

	@Override
	public List<ShoppingCart> getAllShoppingCart() {
		return shoppingCartRepository.getAllShoppingCarts();
	}

	@Override
	public ShoppingCart getActiveShoppingCartById(Long shoppingCartId) {
		Optional<ShoppingCart> shoppingCartDb = shoppingCartRepository.getActiveShoppingCartByPk(shoppingCartId);
		if(shoppingCartDb.isPresent()) {
			return shoppingCartDb.get();
		} else {
			throw new ResourceNotFoundException("El carrito con id: " + shoppingCartId + " no existe o ya fue finalizado");
		}
	}

	@Override
	public ShoppingCart getActiveShoppingCartByUser(String username) {
		Optional<ShoppingCart> shoppingCartDb = shoppingCartRepository.getUserActiveShoppingCart(username);
		if(shoppingCartDb.isPresent()) {
			return shoppingCartDb.get();
		} else {
			return createShoppingCartForUser(username);
		}
	}

	@Override
	public ShoppingCart createShoppingCartForUser(String username) {
		Optional<ShoppingCart> shoppingCartDb = shoppingCartRepository.getUserActiveShoppingCart(username);
		if(!shoppingCartDb.isPresent()) {
			User user = userService.getUserByPk(username);
			ShoppingCart shoppingCart;
			if(user.getVip()) {
				shoppingCart = new ShoppingCartVIP();
			} else if(dateService.isPromoDay()) {
				shoppingCart = new ShoppingCartPromotional();
			} else {
				shoppingCart = new ShoppingCartRegular();
			}
			shoppingCart.setUsername(user.getUsername());
			shoppingCart.setCreationDate(dateService.getActualDate());
			return shoppingCartRepository.createShoppingCart(shoppingCart);
		} else {
			return shoppingCartDb.get();
		}
	}

	@Override
	public ShoppingCart addItem(Long id, CartItemRequest cartItemRequest) {
		Optional<ShoppingCart> shoppingCartDb = shoppingCartRepository.getActiveShoppingCartByPk(id);
		if(shoppingCartDb.isPresent()) {
			ShoppingCart shoppingCart = shoppingCartDb.get();
			if(!shoppingCart.hasItem(cartItemRequest.getItemId())) {
				Item item = itemService.getItemById(cartItemRequest.getItemId());
				CartItem cartItem = new CartItem();
				cartItem.setItem(item);
				cartItem.setQuantity(cartItemRequest.getQuantity());
				cartItem = cartItemService.createCartItem(cartItem);
				shoppingCart.addItem(cartItem);
			} else {
				shoppingCart.addItemQuantity(cartItemRequest);
			}
			return shoppingCartRepository.updateShoppingCart(shoppingCart);
		} else {
			throw new ResourceNotFoundException("El carrito con id: " + id + " no existe o ya fue finalizado");
		}
	}

	@Override
	public ShoppingCart removeItemQuantity(Long id, CartItemRequest cartItemRequest) {
		Optional<ShoppingCart> shoppingCartDb = shoppingCartRepository.getActiveShoppingCartByPk(id);
		if(shoppingCartDb.isPresent()) {
			ShoppingCart shoppingCart = shoppingCartDb.get();
			shoppingCart.removeItemQuantity(cartItemRequest.getItemId(), cartItemRequest.getQuantity());
			return shoppingCartRepository.updateShoppingCart(shoppingCart);
		} else {
			throw new ResourceNotFoundException("El carrito con id: " + id + " no existe o ya fue finalizado");
		}
	}

	@Override
	public ShoppingCart removeItem(Long shoppingCartId, Long itemId) {
		Optional<ShoppingCart> shoppingCartDb = shoppingCartRepository.getActiveShoppingCartByPk(shoppingCartId);
		if(shoppingCartDb.isPresent()) {
			ShoppingCart shoppingCart = shoppingCartDb.get();
			shoppingCart.removeItem(itemId);
			return shoppingCartRepository.updateShoppingCart(shoppingCart);
		} else {
			throw new ResourceNotFoundException("El carrito con id: " + shoppingCartId + " no existe o ya fue finalizado");
		}
	}

	@Override
	public ShoppingCart finishShoppingCart(Long id) {
		Optional<ShoppingCart> shoppingCartDb = shoppingCartRepository.getActiveShoppingCartByPk(id);
		if(shoppingCartDb.isPresent()) {
			ShoppingCart shoppingCart = shoppingCartDb.get();
			shoppingCart.setFinished(true);
			shoppingCartRepository.updateShoppingCart(shoppingCart);
			if(checkUserForVip(shoppingCart.getUsername())){
				userService.doUserVip(shoppingCart.getUsername());
			}
			return getActiveShoppingCartByUser(shoppingCart.getUsername());
		} else {
			throw new ResourceNotFoundException("El carrito con id: " + id + " no existe o ya fue finalizado");
		}
	}

	private Boolean checkUserForVip(String username) {
		LocalDate actualDate = dateService.getActualDate();
		actualDate = LocalDate.of(actualDate.getYear(), actualDate.getMonthValue(), 1);
		Double totalForMonth = shoppingCartRepository.getTotalForMonth(actualDate);
		return totalForMonth > 10000;
	}

	@Override
	public ShoppingCart emptyShoppingCart(Long id) {
		Optional<ShoppingCart> shoppingCartDb = shoppingCartRepository.getActiveShoppingCartByPk(id);
		if(shoppingCartDb.isPresent()) {
			ShoppingCart shoppingCart = shoppingCartDb.get();
			shoppingCart.removeAllItems();
			return shoppingCartRepository.updateShoppingCart(shoppingCart);
		} else {
			throw new ResourceNotFoundException("El carrito con id: " + id + " no existe o ya fue finalizado");
		}
	}

	@Override
	public void cleanOldShoppingCarts() {
		List<ShoppingCart> shoppingCarts = shoppingCartRepository.getUnfinishedShoppingCarts();
		for (ShoppingCart shoppingCart : shoppingCarts) {
			shoppingCartRepository.deleteShoppingCart(shoppingCart);
		}
	}

	@Override
	public Boolean checkVipStatusForUser(String username) {
		LocalDate actualDate = dateService.getActualDate();
		LocalDate checkDate = LocalDate.of(actualDate.getYear(), actualDate.getMonthValue() - 1, 1);
		Integer buys = shoppingCartRepository.getNumberCartsByDateForUser(username, checkDate);
		return buys > 0;
	}
}
