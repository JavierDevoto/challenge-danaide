package com.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    protected Long id;
    protected Item item;
    protected Integer quantity;

    @JsonIgnore
    public Double getTotal() {
        return item.getPrice() * quantity;
    }

    @JsonIgnore
    public Integer removeQuantity(Integer quantity) {
        this.quantity -= quantity;
        return this.quantity;
    }

}
