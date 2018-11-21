package com.java.shoppingTest;

import java.util.ArrayList;

public class Basket {
	
	ArrayList<Book> bookList = new ArrayList<Book>();

	public ArrayList<Book> getBooksInBasket() {
		
		return bookList;
		
	}

	public void addBook(Book book) {
		bookList.add(book);
		
	}

}
