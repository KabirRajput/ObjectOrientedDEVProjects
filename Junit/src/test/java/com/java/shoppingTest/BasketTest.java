package com.java.shoppingTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Test;

public class BasketTest {

	
	@Test
	public void test_GetBooksInBasket_ReturnsEmptyBookList_IfNoBooksHaveBeenAdded(){
		
		//arrange 
		Basket basket = new Basket();
		
		//act
		ArrayList<Book> result = basket.getBooksInBasket();
		
		
		//assert
		assertTrue(result.isEmpty());
	
		
	}
	
	
	@Test
	public void test_GetBooksInBasket_ReturnsArrayOfLengthOne_AfterAddBookMethodIsCalledWithOneBook(){
		
		//arrange 
		Basket basket = new Basket();
		Book book = new Book();
		
		//act
		basket.addBook(book);
		ArrayList<Book> result = basket.getBooksInBasket();
		
		
		
		//assert
		assertEquals(1, result.size());
		
	}
	
	
	@Test
	public void test_GetBooksInBasket_ReturnsArrayOfLengthTwo_AfterAddBookMethodIsCalledWithOneBook(){
		
		//arrange 
		Basket basket = new Basket();
		Book book = new Book();
		Book book2 = new Book();
		
		//act
		basket.addBook(book);
		basket.addBook(book2);
		ArrayList<Book> result = basket.getBooksInBasket();
		
		
		//assert
		assertEquals(2, result.size());
		
	}
	
	

}
