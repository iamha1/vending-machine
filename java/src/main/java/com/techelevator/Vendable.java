package com.techelevator;

import java.math.BigDecimal;

public interface Vendable {

	String makeSound();

	String getSlotName();

	int getQuantity();
	
	void setQuantity(int quantity);
	
	String getProductName();

	BigDecimal getPrice();
	
	String getPriceF();

	String getProductDisplayName();

	//String getPriceDisplay(BigDecimal Price);
		
}