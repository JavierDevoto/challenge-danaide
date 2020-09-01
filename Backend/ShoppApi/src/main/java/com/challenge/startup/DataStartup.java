package com.challenge.startup;

import com.challenge.model.Item;
import com.challenge.model.User;
import com.challenge.service.DateServiceImpl;
import com.challenge.service.ItemServiceImpl;
import com.challenge.service.ShoppingCartServiceImpl;
import com.challenge.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataStartup {

    @Autowired
    private DateServiceImpl dateService;

    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ItemServiceImpl itemService;

    @PostConstruct
    public void executeStartup() {
        //Users
        User user1 = new User("User1", "Nombre1", "Apellido1", true);
        User user2 = new User("User2", "Nombre2", "Apellido2", false);
        userService.createUser(user1);
        userService.createUser(user2);

        //Items
        Item item1 = new Item();
        item1.setName("Item1");
        item1.setDescription("Descripcion1");
        item1.setPrice(1000d);
        Item item2 = new Item();
        item2.setName("Item2");
        item2.setDescription("Descripcion2");
        item2.setPrice(2000d);
        itemService.createItem(item1);
        itemService.createItem(item2);
        System.out.println("Se inicializan tablas");
    }

}
