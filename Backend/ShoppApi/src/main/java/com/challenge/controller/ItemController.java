package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Item;
import net.javaguides.springboot.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping("/items")
	public ResponseEntity<List<Item>> getAllItems(){
		return ResponseEntity.ok().body(itemService.getAllItems());
	}
	
	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable long id){
		return ResponseEntity.ok().body(itemService.getItemById(id));
	}
	
	@PostMapping("/items")
	public ResponseEntity<Item> createItem(@RequestBody Item item){
		return ResponseEntity.ok().body(this.itemService.createItem(item));
	}
	
	@PutMapping("/items/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable long id, @RequestBody Item item){
		item.setId(id);
		return ResponseEntity.ok().body(this.itemService.updateItem(item));
	}

	@DeleteMapping("/items/{id}")
	public HttpStatus deleteItem(@PathVariable long id){
		this.itemService.deleteItem(id);
		return HttpStatus.OK;
	}
}
