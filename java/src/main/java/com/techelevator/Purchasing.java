package com.techelevator;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Purchasing {
	private BigDecimal balance = new BigDecimal(0);

	public Purchasing() {
		//empty constructor
	}
	
	public static String currencyFormat(BigDecimal n) {
	    return NumberFormat.getCurrencyInstance().format(n);
	}
	
	public BigDecimal addMoney(BigDecimal amountToAdd) {
		balance = balance.add(amountToAdd);
		VendingMachineCLI.logger.log("FEED MONEY: " + currencyFormat(amountToAdd) + " " + currencyFormat(balance));	
		return balance;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal newBalance) {
		this.balance = newBalance;
	}
	
	public String getBalanceF() {
		return currencyFormat(balance);
	}
	
	//imagine:
	//balance is 20
	//cost is 3
	//return 17

//		BigDecimal Comparison Codes:
//    	0 means Both values are equal
//    	1 means First Value is greater 
//     -1 means Second value is greater
		
	public boolean checkIfUserHasFedInEnoughMoney(BigDecimal cost) {
		boolean bool = false;		
		int compareBD = balance.compareTo(cost);
		
		if(compareBD >= 0) {
			//User has put in enough money to cover the cost.
			bool = true;
		}
		else {
			//User has has not put in enough money to cover the cost.
			bool = false;
		}
		return bool;
	}
	
	public boolean purchase(Vendable v) {
		boolean bool = false;
		
		if (v.getQuantity() > 0) {
			balance = balance.subtract(v.getPrice());
			v.setQuantity(v.getQuantity() - 1);	
		}
		else {
			bool = false;
			System.out.println("No remaining item of this type!");
			
		}
		return bool;
	}
	
	public String getChangeFromBigDecimal(BigDecimal balance) {
		final BigDecimal quarterValue = new BigDecimal(".25");
		final BigDecimal dimeValue    = new BigDecimal(".10");
		final BigDecimal nickelValue  = new BigDecimal(".05");
		
		String stringBuilder = "";

		int quarters = 0;
		int dimes = 0;
		int nickels = 0;

		while(balance.compareTo(quarterValue) >= 0) {
			quarters++;
			balance = balance.subtract(quarterValue);
		}
		
		while(balance.compareTo(dimeValue) >= 0) {
			dimes++;
			balance = balance.subtract(dimeValue);
		}
		
		while(balance.compareTo(nickelValue) >= 0) {
			nickels++;
			balance = balance.subtract(nickelValue);
		}
		
		
		stringBuilder += "Quarters: " + quarters +"\n";
		stringBuilder += "Dimes: "    + dimes    + "\n";
		stringBuilder += "Nickels: "  + nickels  + "\n";
		
		return stringBuilder;
	}
	
	
	
	
	public void returnChange() {
		BigDecimal temp = this.balance;
		System.out.println("Here is your change: "); // + this.getBalanceF());

		System.out.println(getChangeFromBigDecimal(this.balance));
		
		balance = new BigDecimal(0);
		VendingMachineCLI.logger.log("GIVE CHANGE: " + currencyFormat(temp) + " " + this.getBalanceF());

	}
	
	public BigDecimal purchase (BigDecimal cost) {
		
		//if(checkIfUserHasFedInEnoughMoney(cost)) { 
			//balance -= cost;
			balance = balance.subtract(cost);
		//}
		return balance;
	}
}
