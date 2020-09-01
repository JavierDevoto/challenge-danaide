package com.challenge.controller;

import java.util.List;

import com.challenge.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.challenge.model.Item;
import com.challenge.service.ItemService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemServiceImpl itemService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Item>> getAllItems(){
		return ResponseEntity.ok().body(itemService.getAllItems());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable Long id){
		return ResponseEntity.ok().body(itemService.getItemById(id));
	}
	
	@PostMapping("/create")
	public ResponseEntity<Item> createItem(@RequestBody Item item){
		return ResponseEntity.ok().body(itemService.createItem(item));
	}

}
