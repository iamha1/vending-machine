package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Logger;

import com.techelevator.view.Menu;

public class VendingMachineCLI {
	private Menu menu;
	private Inventory inventory;
	private Purchasing purchasing;
	public static VendingLogger logger;
	public SalesReport salesReport;
	

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;

		this.inventory = new Inventory();
		inventory.getInventory("vendingmachine.csv");

		this.purchasing = new Purchasing();
		
		

		try {
			this.logger = new VendingLogger("Log.txt");
			this.salesReport = new SalesReport("SalesReport.txt");
		} catch (IOException e) {
			System.out.println("Fatal error in Log File setup!!!");
			System.exit(-1);
		}
	}

//		private void clearScreen() {
//			for(int i=0; i<50; i++) {
//				System.out.println(" ");
//			}
//		}
	// Main Menu
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_HIDDEN = " ";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_HIDDEN };
	

	public void run() {
		while (true) {
			// clearScreen();

			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println("|<|>|<|>|<|>|<| Main Menu |>|<|>|<|>|<|>|");
			System.out.println("-----------------------------------------");

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// Display full inventory.
				System.out.println(inventory.toString());
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// Move to Purchasing Menu Loop
				purchasingMenu();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				// Exit Vendo-Matic 800 Program
				
				//return change:
				purchasing.returnChange();
				
				//write sales report
				
				System.out.println("Thank you for choosing us!");
				System.out.println("-----Have a great day----!");
				try {
					logger.close();
				} catch (IOException e) {
				}
				;
				System.exit(0);
			}
			 else if (choice.equals(MAIN_MENU_OPTION_HIDDEN))	{
					salesReport.writeSalesReport();
			}
		}
	}

	// Purchasing Menu
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT,
			PURCHASE_MENU_FINISH_TRANSACTION };

	public void purchasingMenu() {
		boolean purchasingKeepGoing = true;
		while (purchasingKeepGoing == true) {
			// clearScreen();

			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println("|<|>|<|>|<|>| Purchase Menu |<|>|<|>|<|>|");
			System.out.println("-----------------------------------------");

			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			if (choice.equals(PURCHASE_MENU_FEED_MONEY)) {
				feedMoneyMenu();
			} else if (choice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
				// Select Product
				selectProdcutMenu();
			} else if (choice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
				// Finish Purchasing Transaction
				purchasingKeepGoing = false;
			}
		}
	}

	// Feed Money Menu
	private static final String FEED_MONEY_MENU_ONE_DOLLAR = "One Dollar Bill";
	private static final String FEED_MONEY_MENU_TWO_DOLLAR = "Two Dollar Bill";
	private static final String FEED_MONEY_MENU_FIVE_DOLLAR = "Five Dollar Bill";
	private static final String FEED_MONEY_MENU_TEN_DOLLAR = "Ten Dollar Bill";
	private static final String FEED_MONEY_MENU_STOP_FEEDING = "Stop Feeding Money In";
	private static final String[] FEED_MONEY_MENU_OPTIONS = { FEED_MONEY_MENU_ONE_DOLLAR, FEED_MONEY_MENU_TWO_DOLLAR,
			FEED_MONEY_MENU_FIVE_DOLLAR, FEED_MONEY_MENU_TEN_DOLLAR, FEED_MONEY_MENU_STOP_FEEDING };

	public void feedMoneyMenu() {
		boolean feedMoneyKeepGoing = true;
		while (feedMoneyKeepGoing == true) {
			// clearScreen();

			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println("|<|>|<|>|<|>|<| Feed Money >|<|>|<|>|<|>|");
			System.out.println("-----------------------------------------");

			System.out.println("\n Your current balance is: " + purchasing.getBalanceF());
			String choice = (String) menu.getChoiceFromOptions(FEED_MONEY_MENU_OPTIONS);
			if (choice.equals(FEED_MONEY_MENU_ONE_DOLLAR)) {
				System.out.println("Try 1 dollar!");
				purchasing.addMoney(new BigDecimal(1));

			} else if (choice.equals(FEED_MONEY_MENU_TWO_DOLLAR)) {
				purchasing.addMoney(new BigDecimal(2));
			} else if (choice.equals(FEED_MONEY_MENU_FIVE_DOLLAR)) {
				purchasing.addMoney(new BigDecimal(5));
			} else if (choice.equals(FEED_MONEY_MENU_TEN_DOLLAR)) {
				purchasing.addMoney(new BigDecimal(10));
			} else if (choice.equals(FEED_MONEY_MENU_STOP_FEEDING)) {
				feedMoneyKeepGoing = false;
			}
		}
	}

	public void selectProdcutMenu() {

		String codeFromUser = "";

		while (codeFromUser.equals("")) {
			// Display list of product again:
			System.out.println(inventory.toString());
			codeFromUser = inventory.getCodeFromUser();
		}

		Vendable v = inventory.getVendableFromCode(codeFromUser);
		int quantity = v.getQuantity();
		BigDecimal price = v.getPrice();

		if (quantity <= 0) {
			// handle the situation where there are none left.
			System.out.println("This product is sold out. Sorry!");
		} else {

			if (purchasing.checkIfUserHasFedInEnoughMoney(price)) {
				System.out.println("You have enough money!");
				System.out.println("Your balance is: " + purchasing.getBalanceF());
				System.out.println("Your item costs: " + v.getPriceF());
				System.out.println(v.makeSound());
				
				salesReport.countSales(v);
				
				
				logger.log(v.getProductName() + " " + v.getSlotName() + " " + v.getPriceF() + " "
						+ purchasing.getBalanceF());
				// return leftover balance:
				purchasing.purchase(v);
			} else {
				System.out.println("Please add in more money!");
				System.out.println("You only have: " + purchasing.getBalanceF());
				System.out.println("Your item costs: " + v.getPriceF());
			}

		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println();
		System.out.println("|=======================================|");
		System.out.println("|<<<<<<<<<<| =============== |>>>>>>>>>>|");
		System.out.println("|==========| Vendo-Matic 800 |==========|");
		System.out.println("|<<<<<<<<<<| =============== |>>>>>>>>>>|");
		System.out.println("|=======================================|");
		System.out.println();
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);

		cli.run();

	}
}