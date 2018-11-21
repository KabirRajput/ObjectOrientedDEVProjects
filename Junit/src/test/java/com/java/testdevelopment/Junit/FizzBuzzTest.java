package com.java.testdevelopment.Junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FizzBuzzTest {
	
	@Test
	public void test_1_is_evaluated_to1() {
		
		//arrange
		FizzBuzz fb = new FizzBuzz();
		
		//act
		String result = fb.evaluate(1);
		
		//assert
		assertEquals("1",result);
		
	}
	@Test
	public void test_2_is_evaluated_to1() {
		
		//arrange
		FizzBuzz fb = new FizzBuzz();
		
		//act
		String result = fb.evaluate(2);
		
		//assert
		assertEquals("2",result);
		
	}
	@Test
	public void test_3_is_evaluated_to_FIZZ() {
		
		//arrange
		FizzBuzz fb = new FizzBuzz();
		
		//act
		String result = fb.evaluate(3);
		
		//assert
		assertEquals("FIZZ",result);
		
	}
	
	@Test
	public void test_4_is_evaluated_to_BUZZ() {
		
		//arrange
		FizzBuzz fb = new FizzBuzz();
		
		//act
		String result = fb.evaluate(5);
		
		//assert
		assertEquals("BUZZ",result);
		
	}
	
	@Test
	public void test_5_is_evaluated_to_6() {
		
		//arrange
		FizzBuzz fb = new FizzBuzz();
		
		//act
		String result = fb.evaluate(6);
		
		//assert
		assertEquals("FIZZ",result);
	}
	
	@Test
	public void test_6_is_evaluated_to_10() {
		
		//arrange
		FizzBuzz fb = new FizzBuzz();
		
		//act
		String result = fb.evaluate(10);
		
		//assert
		assertEquals("BUZZ",result);
		
	}
	
	@Test
	public void test_7_is_evaluated_to_15() {
		
		//arrange
		FizzBuzz fb = new FizzBuzz();
		
		//act
		String result = fb.evaluate(15);
		
		//assert
		assertEquals("FIZZBUZZ",result);
		
	}


}
