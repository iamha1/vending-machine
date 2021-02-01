package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class CandyTest {

	@Test
	public void get_sound_from_selling_candy() {
		
		//arrange
	
		Candy candy = new Candy("A1", "Life-saver", new BigDecimal("1.25")); 
			
		String expected = "Munch, Munch, Yum!";
		
		//act
		String actual = candy.makeSound();
		
		//assert
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void get_slotname() {
		
		//arrange
	
		Candy candy = new Candy("A1", "Life-saver", new BigDecimal("1.25")); 
			
		String expected = "A1";
		
		//act
		String actual = candy.getSlotName();
		
		//assert
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void get_quantity() {
		
		//arrange
	
		Candy candy = new Candy("A1", "Life-saver", new BigDecimal("1.25")); 
			
		int expected = 5;
		
		//act
		int actual = candy.getQuantity();
		
		//assert
		
		assertEquals(expected, actual);
	}

}
