package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class PurchasingTest {
	
	@Test
	public void get_change_from_BigDECIMAL() {
		
		//arrange		
		Purchasing purchasing  = new Purchasing();
		
		String expected = "Quarters: 6\n";
		expected += "Dimes: 1\n";
		expected += "Nickels: 1\n";
		
		//act
		BigDecimal inputBalance = new BigDecimal("1.65");
		String actual = purchasing.getChangeFromBigDecimal(inputBalance);
		
		//assert	
		assertEquals(expected, actual);
	}
	
	@Test
	public void get_balance() {
		
		//arrange		
		Purchasing purchasing  = new Purchasing();
		
		BigDecimal inputBalance = new BigDecimal("1.65");
		BigDecimal expected = new BigDecimal("1.65");

		//act
		purchasing.setBalance(inputBalance);
		BigDecimal actual = purchasing.getBalance();
		
		//assert	
		assertEquals(expected, actual);
	}
	@Test
	public void currency_format() {
		
		//arrange			
		String expected = ("$1.65");
		
		//act
		BigDecimal inputBalance = new BigDecimal("1.65");
		String actual = Purchasing.currencyFormat(inputBalance);
		
		//assert	
		assertEquals(expected, actual);
	}
	
}
