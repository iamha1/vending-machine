package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Item implements Vendable {

	public Gum(String slotName, String productName, BigDecimal price) {
		super(slotName, productName, price);
	}
	
	@Override
	public String makeSound() {
		return "Chew, Chew, Yum!";
	}

}
