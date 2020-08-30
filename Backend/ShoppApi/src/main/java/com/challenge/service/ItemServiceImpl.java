package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Item;
import net.javaguides.springboot.repository.ItemRepository;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item createItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item updateItem(Item item) {
		Optional<Item> productDb = this.itemRepository.findById(item.getId());
		
		if(productDb.isPresent()) {
			Item itemUpdate = productDb.get();
			itemUpdate.setId(item.getId());
			itemUpdate.setName(item.getName());
			itemUpdate.setDescription(item.getDescription());
			itemRepository.save(itemUpdate);
			return itemUpdate;
		}else {
			throw new ResourceNotFoundException("No se encontró item con id: " + item.getId());
		}		
	}

	@Override
	public List<Item> getAllItems() {
		return this.itemRepository.findAll();
	}

	@Override
	public Item getItemById(Long itemId) {
		Optional<Item> itemDb = this.itemRepository.findById(itemId);
		
		if(itemDb.isPresent()) {
			return itemDb.get();
		}else {
			throw new ResourceNotFoundException("No se encontró item con id: " + itemId);
		}
	}

	@Override
	public void deleteItem(Long itemId) {
		Optional<Item> itemDb = this.itemRepository.findById(itemId);
		
		if(itemDb.isPresent()) {
			this.itemRepository.delete(itemDb.get());
		}else {
			throw new ResourceNotFoundException("No se encontró item con id: " + itemId);
		}
	}

}
