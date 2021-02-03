package com.techelevator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Item {

	private static int longestProductName;
	private String slotName;
	private String productName;
	private BigDecimal price;
	private int quantity;

	public Item(String slotName, String productName, BigDecimal price) {
		this.slotName = slotName;
		this.productName = productName;
		this.price = price;
		this.quantity = 5;
	}

	public abstract String makeSound();

	public static void setLongestProductName(int length) {
		longestProductName = (length > longestProductName) ? length : longestProductName;
	}

	public String getSlotName() {
		return slotName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public String getPriceF() {
		return Purchasing.currencyFormat(getPrice());
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	// Print out Items
	public String getProductDisplayName() {
		List<Character> fixedWidthName = new ArrayList<>();
		for (int i = 0; i < productName.length(); i++) {
			fixedWidthName.add(productName.charAt(i));
		}
		int x = longestProductName - productName.length() + 3;
		for (int i = 0; i < x; i++) {
			fixedWidthName.add('-');
		}
		String productDisplayName = "";
		for (Character c : fixedWidthName) {
			productDisplayName += c;
		}
		return productDisplayName;
	}

	@Override
	public String toString() {
		String stringBuilder = "";
		if (this.quantity == 0) {
			stringBuilder = slotName + "---" + "SOLD OUT!";
		} else {
			stringBuilder += slotName + "---";
			stringBuilder += getProductDisplayName();
			stringBuilder += getPriceF();		
			stringBuilder += " Quantity: " + quantity;
		}
		return stringBuilder;
	}
}
