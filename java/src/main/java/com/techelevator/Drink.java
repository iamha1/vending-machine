package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Item implements Vendable {

	public Drink(String slotName, String productName, BigDecimal price) {
		super(slotName, productName, price);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String makeSound() {
		return "Glug, Glug, Yum!";
	}

}
