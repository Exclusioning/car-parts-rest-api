package com.mycompany.carpartsapp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CarPartInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int partId;

	String partName;
	String partPrice;
	int partQuantity;
	
	protected CarPartInfo(){}

	public CarPartInfo(String pName, String pPrice, int pId, int pQuantity){
		this.partName = pName;
		this.partPrice = pPrice;
		this.partId = pId;
		this.partQuantity = pQuantity;
	}

	public String getPartName(){
        	return partName;
     	}
     
     	public String getPartPrice(){
         	return partPrice;
     	}
     
     	public int getPartId(){
         	return partId;
     	}
     
     	public int getPartQuantity(){
         	return partQuantity;
     	}
     
     	public String toString(){
         	return partName + " | $" + partPrice + " | Stock ID: " + partId + " | In Stock: " + partQuantity;
     	}
     
     	public void setQuantity(int partQuantity){
         	this.partQuantity = partQuantity;
     	}

     	public void setPartName(String partName){
     	 	this.partName = partName;
     	}
     
     	public void setPartId(int partId){
     	 	this.partId = partId;
     	}
    
     	public void setPartPrice(String partPrice){
     		this.partPrice = partPrice;
     	}
}
