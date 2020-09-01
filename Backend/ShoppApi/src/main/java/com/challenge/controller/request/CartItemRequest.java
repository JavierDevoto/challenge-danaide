package com.challenge.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequest {

    protected Long itemId;
    protected Integer quantity;

}
