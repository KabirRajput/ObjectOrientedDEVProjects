package com.java.shoppingTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CheckoutTest {
	
	@Test
	public void test_CalculatePrice_ReturnsDoubleZeroPointZeroWhenPassedAnEmptyBasket(){
		
		//arrange
		Basket basket = new Basket();
		Checkout checkout = new Checkout();
				
		//act
		double result = checkout.calculatePrice(basket);
		
		//assert
		assertEquals(0.0, result,0.001);
	}
	
	@Test
	public void test_CalculatePrice_ReturnsPriceOfBookInBasket_WhenPassedBasketWithOneBook(){
		
		//arrange
		Basket basket = new Basket();
		Checkout checkout = new Checkout();
		Book book = new Book();
				
		//act
		book.setprice(100);
		basket.addBook(book);
		double result = checkout.calculatePrice(basket);
		
	
		//assert
		assertEquals(100.0, result,0.0001);
	}
	
	@Test
	public void test_CalculatePrice_ReturnsPriceOfBookInBasket_WhenPassedBasketWithTwoBook(){
		
		//arrange
		Basket basket = new Basket();
		Checkout checkout = new Checkout();
		Book book = new Book();
		Book book2 = new Book();
				
		//act
		book.setprice(100);
		book2.setprice(1100);
		basket.addBook(book);
		basket.addBook(book2);
		
		double result = checkout.calculatePrice(basket);
		
	
		//assert
		assertEquals(1200.0, result,0.0001);
	}
	
	@Test
	public void test_CalculatePrice_ReturnsPriceOfBookInBasket_WhenPassedBasketWithThreeBook(){
		
		//arrange
		Basket basket = new Basket();
		Checkout checkout = new Checkout();
		Book book = new Book();
		Book book2 = new Book();
		Book book3 = new Book();
				
		//act
		book.setprice(100);
		book2.setprice(100);
		book3.setprice(100);
		basket.addBook(book);
		basket.addBook(book2);
		basket.addBook(book3);
		
		double result = checkout.calculatePrice(basket);
		
	
		//assert
		assertEquals(297.0, result,0.0001);
	}
	
	@Test
	public void test_CalculatePrice_ReturnsPriceOfBookInBasket_WhenPassedBasketWithSevenBook(){
		
		//arrange
		Basket basket = new Basket();
		Checkout checkout = new Checkout();
		Book book = new Book();
		Book book2 = new Book();
		Book book3 = new Book();
		Book book4 = new Book();
		Book book5 = new Book();
		Book book6 = new Book();
		Book book7 = new Book();
	
				
		//act
		book.setprice(100);
		book2.setprice(100);
		book3.setprice(100);
		book4.setprice(100);
		book5.setprice(100);
		book6.setprice(100);
		book7.setprice(100);
		
		basket.addBook(book);
		basket.addBook(book2);
		basket.addBook(book3);
		basket.addBook(book4);
		basket.addBook(book5);
		basket.addBook(book6);
		basket.addBook(book7);
		
		
		double result = checkout.calculatePrice(basket);
		
	
		//assert
		assertEquals(686.0, result,0.0001);
	}


}
