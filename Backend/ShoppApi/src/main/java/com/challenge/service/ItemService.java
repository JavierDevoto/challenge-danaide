package com.challenge.service;

import java.util.List;

import com.challenge.model.Item;

public interface ItemService {

	Item createItem(Item item);

	List<Item> getAllItems();

	Item getItemById(Long itemId);

}
