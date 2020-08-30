package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.model.Item;

public interface ItemService {

	Item createItem(Item item);

	Item updateItem(Item item);

	List<Item> getAllItems();

	Item getItemById(Long itemId);

	void deleteItem(Long itemId);

}
