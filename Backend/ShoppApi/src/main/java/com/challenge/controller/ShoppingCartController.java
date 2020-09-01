package com.challenge.controller;

import com.challenge.controller.request.CartItemRequest;
import com.challenge.model.ShoppingCart;
import com.challenge.service.ShoppingCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartServiceImpl shoppingCartService;

	@GetMapping("/create/{username}")
	public ResponseEntity<ShoppingCart> getNewShoppingCartForUser(@PathVariable String username){
		return ResponseEntity.ok().body(shoppingCartService.createShoppingCartForUser(username));
	}

	@GetMapping("/all")
	public ResponseEntity<List<ShoppingCart>> getAllShoppingCart(){
		return ResponseEntity.ok().body(shoppingCartService.getAllShoppingCart());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable Long id){
		return ResponseEntity.ok().body(shoppingCartService.getActiveShoppingCartById(id));
	}

	@GetMapping("/get-active-user-shopp/{username}")
	public ResponseEntity<ShoppingCart> getShoppingCartByUser(@PathVariable String username){
		return ResponseEntity.ok().body(shoppingCartService.getActiveShoppingCartByUser(username));
	}

	@PostMapping("/add-item/{id}")
	public ResponseEntity<ShoppingCart> addItem(@PathVariable Long id, @RequestBody CartItemRequest cartItemRequest){
		return ResponseEntity.ok().body(shoppingCartService.addItem(id, cartItemRequest));
	}

	@PostMapping("/remove-item-quantity/{id}")
	public ResponseEntity<ShoppingCart> removeItemQuantity(@PathVariable Long id, @RequestBody CartItemRequest cartItemRequest){
		return ResponseEntity.ok().body(shoppingCartService.removeItemQuantity(id, cartItemRequest));
	}

	@PostMapping("/remove-item/{id}")
	public ResponseEntity<ShoppingCart> removeItem(@PathVariable Long id, @RequestBody CartItemRequest cartItemRequest){
		return ResponseEntity.ok().body(shoppingCartService.removeItem(id, cartItemRequest.getItemId()));
	}

	@PostMapping("/finish/{id}")
	public ResponseEntity<ShoppingCart> removeItem(@PathVariable Long id){
		return ResponseEntity.ok().body(shoppingCartService.finishShoppingCart(id));
	}

	@PostMapping("/empty-shopp/{id}")
	public ResponseEntity<ShoppingCart> emptyShoppingCart(@PathVariable Long id){
		return ResponseEntity.ok().body(shoppingCartService.emptyShoppingCart(id));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteShoppingCart(@PathVariable Long id){
		shoppingCartService.deleteShoppingCart(id);
		return ResponseEntity.ok().body("OK");
	}

}
