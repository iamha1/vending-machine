package com.techelevator;

import java.io.File;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {

	private List<Vendable> vendableList = new ArrayList<>();

	public Inventory() {
		// empty constructor
	}
	
	// toString method for inventory
	
	public String toString() {
		String stringBuilder = "";
		
		for (Vendable i: vendableList) {
			stringBuilder += i.toString();	
			stringBuilder += "\n";
		}
		return stringBuilder;
	}
	
	public String[] getMenuOptions() {
		String[] stringArrayBuilder = new String[vendableList.size()];
		String stringBuilder= "";
		Vendable v;
		for(int i=0; i<stringArrayBuilder.length; i++) {
			v = vendableList.get(i);
			stringBuilder = "";
			stringBuilder += v.getProductName() + " " + v.getPriceF();
			stringArrayBuilder[i] = stringBuilder;
		}
		return stringArrayBuilder;
	}
	
	public BigDecimal getCostFromMenuNumber(int menuNumber) {
		int listIndexNumber = menuNumber - 1; 
		BigDecimal itemCost = vendableList.get(listIndexNumber).getPrice();
		
		return itemCost;		
	}
	
	public Vendable getVendableFromCode(String code) {
		for (Vendable vendable: vendableList) {
			if (vendable.getSlotName().equals(code)) {
				return vendable;
			}
		}	
		return null;
	}
	
	public String getCodeFromUser() {	
		System.out.println("Please enter the code of your item: ");
		Scanner userInput = new Scanner(System.in);
		String code = userInput.nextLine();
		code = code.toUpperCase();
		Vendable v = getVendableFromCode(code);
		if (v == null) {
			code = "";
			System.out.println("You have entered an invalid code!");
		}
		return code;	
	}
	
	public void getInventory(String filePath) {

		File inventoryFile = new File(filePath);

		String line = "";
		String[] splitLines = new String[5];
		
		String slotName;
		String productName;
		BigDecimal price;
		String productType;
		
		try (Scanner scanner = new Scanner(inventoryFile);) {

			while (scanner.hasNextLine()) {
				line = scanner.nextLine();

				splitLines = line.split("\\|");
				
				//Items from the file:		
				slotName = splitLines[0];
				productName = splitLines[1];
				
				//This is used to allow a fixed width display of the product names.
				Item.setLongestProductName(productName.length());
				
				double doublePrice = Double.parseDouble(splitLines[2]);
				price = BigDecimal.valueOf(doublePrice);
				
				productType = splitLines[3];
				
				if(productType.equals("Chip")) {
					Chip currentChip = new Chip(slotName, productName, price);
					vendableList.add(currentChip);
				}
				else if(productType.equals("Drink")) {
					Drink currentChip = new Drink(slotName, productName, price);
					vendableList.add(currentChip);
				}
				else if(productType.equals("Gum")) {
					Gum currentChip = new Gum(slotName, productName, price);
					vendableList.add(currentChip);
				}
				else if(productType.equals("Candy")) {
					Candy currentChip = new Candy(slotName, productName, price);
					vendableList.add(currentChip);
				}
							
				//Adding items from file to the List:
//				currentItem = new Item (slotName, productName, price, productType);
//				itemList.add(currentItem);			
			}	
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
