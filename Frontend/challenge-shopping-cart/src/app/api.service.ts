import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
    private SERVER_BASE_URL = "http://localhost:8080";
    constructor(private httpClient: HttpClient) { }
    
    public getAllItems(){  
        return this.httpClient.get(this.SERVER_BASE_URL + '/item/all');  
    }

    public getActiveShoppingCartByUser(username) {
        return this.httpClient.get(this.SERVER_BASE_URL + '/shopping-cart/get-active-user-shopp/' + username);  
    }

    public addItemToCart(cartItem, cartId) {
        return this.httpClient.post(this.SERVER_BASE_URL + '/shopping-cart/add-item/' + cartId, cartItem);
    }

    public removeItemUnitiToCart(cartItem, cartId) {
        return this.httpClient.post(this.SERVER_BASE_URL + '/shopping-cart/remove-item-quantity/' + cartId, cartItem);
    }

    public removeItemToCart(cartItem, cartId) {
        return this.httpClient.post(this.SERVER_BASE_URL + '/shopping-cart/remove-item/' + cartId, cartItem);
    }

    public finishBuy(cartId) {
        return this.httpClient.post(this.SERVER_BASE_URL + '/shopping-cart/finish/' + cartId, null);
    }

    public emptyCart(cartId) {
        return this.httpClient.post(this.SERVER_BASE_URL + '/shopping-cart/empty-shopp/' + cartId, null);
    }
}