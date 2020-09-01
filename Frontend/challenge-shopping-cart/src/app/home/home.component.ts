import { Component, OnInit } from '@angular/core';  
import { ApiService } from '../api.service';
import { USER } from '../globals' 

@Component({  
	selector: 'app-home',  
	templateUrl: './home.component.html',  
	styleUrls: ['./home.component.css']  
})  
export class HomeComponent implements OnInit {
    items = [];
    cart:any = {};
    constructor(private apiService: ApiService) { }
    
	ngOnInit() {
		this.apiService.getAllItems().subscribe((data:any[])=>{  
			this.items = data;  
        })
		this.apiService.getActiveShoppingCartByUser(USER).subscribe((data:any[])=>{
			this.cart = data;
		})
    }
    
    addItem(item:any) {
        let cartItem:any = {
            itemId: item.id,
            quantity: 1
        };
		this.apiService.addItemToCart(cartItem, this.cart.id).subscribe((data: any[])=>{  
			this.cart = data;
		})
    }
}