package com.mycompany.carpartsapp;

import jakarta.persistence.*;

@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int cartId;

	private int partId;
	private int quantity;
	private String sessionId;
	
	public CartItem(){}

	public CartItem(int partId, int quantity, String sessionId){
		this.partId = partId;
		this.quantity = quantity;
		this.sessionId = sessionId;
	}

	public int getCartId(){
        	return cartId;
     	}
     
     	public int getPartId(){
         	return partId;
     	}
     
     	public int getQuantity(){
         	return quantity;
     	}
     
     	public String getSessionId(){
         	return sessionId;
     	}
     
     	public String toString(){
         	return cartId + partId + quantity + sessionId;
     	}
     
     	public void setCartId(int cartId){
         	this.cartId = cartId;
     	}

     	public void setPartId(int partId){
     	 	this.partId = partId;
     	}
     
     	public void setQuantity(int quantity){
     	 	this.quantity = quantity;
     	}
    
     	public void setPartPrice(String sessionId){
     		this.sessionId = sessionId;
     	}
}
