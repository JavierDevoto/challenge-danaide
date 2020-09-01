package com.challenge.service;

import java.util.List;
import java.util.Optional;

import com.challenge.dbobjects.ItemDB;
import com.challenge.dbobjects.ItemDbMapper;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.repository.ItemRepository;

import com.challenge.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemDbMapper itemDbMapper;

	@Override
	public Item createItem(Item item) {
		ItemDB itemDB = itemRepository.save(itemDbMapper.mappObject(item));
		return itemDbMapper.mappObject(itemDB);
	}

	@Override
	public List<Item> getAllItems() {
		List<ItemDB> itemsDB = itemRepository.findAll();
		return itemDbMapper.mappObjects(itemsDB);
	}

	@Override
	public Item getItemById(Long itemId) {
		Optional<ItemDB> itemDB = itemRepository.findById(itemId);
		if(itemDB.isPresent()) {
			return  itemDbMapper.mappObject(itemDB.get());
		} else {
			throw new ResourceNotFoundException("El item con id: " + itemId + " no fue encontrado");
		}
	}

}
