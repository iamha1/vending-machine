package com.techelevator;

import java.math.BigDecimal;

public class Chip extends Item implements Vendable {

	public Chip(String slotName, String productName, BigDecimal price) {
		super(slotName, productName, price);
		
	}

	@Override
	public String makeSound() {
		return "Crunch, Crunch, Yum!";
	}
}

