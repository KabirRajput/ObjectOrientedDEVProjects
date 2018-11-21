package com.fdmgroup.bullcow1.OOD_1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BullCowTest {
	
	@Test
	public void test1_OneDigit_Always_Returns_No_Cow(){
		
		//arrange
		BullCow bullcow = new BullCow("3");
		String numbers ="3";
		
		//act
		int cows = bullcow.getCows(numbers);

		//assert
		assertEquals(0,cows);
	}
	
	
	@Test
	public void test2_OneDigit_Returns_No_Bull(){

		//arrange
		BullCow bullcow = new BullCow("3");
		String numbers = "4";

		//act
		int bulls = bullcow.getBulls(numbers);
		
		
		//assert
		assertEquals(0,bulls);
	}
	
	@Test
	public void test3_OneDigit_Returns_One_Bull(){
		
		//arrange
		BullCow bullcow = new BullCow("3");
		String numbers ="3";
		
		//act
		int bulls = bullcow.getBulls(numbers);
		
		//assert
		assertEquals(1,bulls);
	}
	
	@Test
	public void test4_Two_Digit_Returns_Zero_Bulls(){
		
		//arrange
		BullCow bullcow = new BullCow("77");
		String numbers ="86";
		
		//act
		int bulls = bullcow.getBulls(numbers);
		
		//assert
		assertEquals(0,bulls);
	}
	
	@Test
	public void test5_Two_Digit_Returns_Zero_Cows(){
		
		//arrange
		BullCow bullcow = new BullCow("77");
		String numbers ="86";
		
		//act
		int cows = bullcow.getCows(numbers);
		
		//assert
		assertEquals(0,cows);
	}
	@Test
	public void test6_Two_Digit_Returns_One_Bull(){
		
		//arrange
		BullCow bullcow = new BullCow("87");
		String numbers ="86";
		
		//act
		int bulls = bullcow.getBulls(numbers);
		
		//assert
		assertEquals(1,bulls);
	}
	
	@Test
	public void test7_Two_Digit_Returns_Two_Bull(){
		
		//arrange
		BullCow bullcow = new BullCow("87");
		String numbers ="87";
		
		//act
		int bulls = bullcow.getBulls(numbers);
		
		//assert
		assertEquals(2,bulls);
	}
	@Test
	public void test8_Two_Digit_Returns_Zero_Cows(){
		
		//arrange
		BullCow bullcow = new BullCow("87");
		String numbers ="87";
		
		//act
		int cows = bullcow.getCows(numbers);
		
		//assert
		assertEquals(0,cows);
	}
	
	@Test
	public void test9_Two_Digit_Returns_One_Cows(){
		
		//arrange
		BullCow bullcow = new BullCow("97");
		String numbers ="87";
		
		//act
		int cows = bullcow.getCows(numbers);
		
		//assert
		assertEquals(0,cows);
	}
	
	@Test
	public void test10_Two_Digit_Returns_Two_Cows(){
		
		//arrange
		BullCow bullcow = new BullCow("91");
		String numbers ="87";
		
		//act
		int cows = bullcow.getCows(numbers);
		
		//assert
		assertEquals(0,cows);
	}
	
	@Test
	public void test11_Three_Digit_Returns_One_Cows(){
		
		//arrange
		BullCow bullcow = new BullCow("357");
		String numbers ="837";
		
		//act
		int cows = bullcow.getCows(numbers);
		
		//assert
		assertEquals(1,cows);
	}
	
	@Test
	public void test12_Three_Digit_Returns_Two_Bull(){
		
		//arrange
		BullCow bullcow = new BullCow("357");
		String numbers ="337";
		
		//act
		int bulls = bullcow.getBulls(numbers);
		
		//assert
		assertEquals(2,bulls);
	}
	
	
	

}
