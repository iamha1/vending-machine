package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Item implements Vendable {

	public Candy(String slotName, String productName, BigDecimal price) {
		super(slotName, productName, price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String makeSound() {
		return "Munch, Munch, Yum!";
	}

}
