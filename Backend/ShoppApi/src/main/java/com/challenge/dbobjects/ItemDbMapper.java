package com.challenge.dbobjects;

import com.challenge.model.Item;
import com.challenge.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemDbMapper {

    public Item mappObject(ItemDB itemDB) {
        Item item = new Item();
        item.setId(itemDB.getId());
        item.setPrice(itemDB.getPrice());
        item.setDescription(itemDB.getDescription());
        item.setName(itemDB.getName());
        return item;
    }

    public ItemDB mappObject(Item item) {
        ItemDB itemDB = new ItemDB();
        itemDB.setId(item.getId());
        itemDB.setPrice(item.getPrice());
        itemDB.setDescription(item.getDescription());
        itemDB.setName(item.getName());
        return itemDB;
    }

    public List<Item> mappObjects(List<ItemDB> itemsDb) {
        List<Item> items = new ArrayList<>();
        for (ItemDB itemDB : itemsDb) {
            items.add(mappObject(itemDB));
        }
        return items;
    }

}
