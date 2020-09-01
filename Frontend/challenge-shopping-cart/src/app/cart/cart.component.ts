import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { USER } from '../globals' 

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
	cart: any = {};
    constructor(private apiService: ApiService) { }
    
	ngOnInit() {
		this.apiService.getActiveShoppingCartByUser(USER).subscribe((data: any[])=>{
			this.cart = data;
		})
	}

    removeItemUnity(item:any) {
        let cartItem:any = {
            itemId: item.item.id,
            quantity: 1
        };
		this.apiService.removeItemUnitiToCart(cartItem, this.cart.id).subscribe((data: any[])=>{  
			this.cart = data;
		})
    }

    removeItem(item:any) {
        let cartItem:any = {
            itemId: item.item.id
        };
		this.apiService.removeItemToCart(cartItem, this.cart.id).subscribe((data: any[])=>{  
			this.cart = data;
		})
    }

    finishBuy() {
		this.apiService.finishBuy(this.cart.id).subscribe((data: any[])=>{  
			this.cart = data;
		})
    }

    emptyCart() {
		this.apiService.emptyCart(this.cart.id).subscribe((data: any[])=>{  
			this.cart = data;
		})
    }

}