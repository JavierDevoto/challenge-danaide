package com.challenge.startup;

import com.challenge.service.ShoppingCartServiceImpl;
import com.challenge.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;

@Component
public class Observer implements PropertyChangeListener {

    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    private UserServiceImpl userService;

    private LocalDate actualDate = LocalDate.now();

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LocalDate newDate = (LocalDate) evt.getNewValue();
        System.out.println("Se cambia de dia a: " + newDate.toString());
        if(actualDate.getDayOfMonth() != newDate.getDayOfMonth()){
            shoppingCartService.cleanOldShoppingCarts();
        }
        if(actualDate.getMonthValue() != newDate.getMonthValue()){
            userService.cleanNoVip();
        }
        actualDate = newDate;
    }

}
