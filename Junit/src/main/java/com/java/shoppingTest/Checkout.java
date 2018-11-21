package com.java.shoppingTest;

public class Checkout {

	public double calculatePrice(Basket basket) {
		
	
		double sum =0;
		double discount1 = 0.01;
		double discount2 = 0.02;
		for (Book b: basket.getBooksInBasket()){
						
			sum += b.getPrice();
						
	}
		if(basket.getBooksInBasket().size()>6) {
			sum = sum -(sum * discount2);
		}
		else if(basket.getBooksInBasket().size()>2) {
			sum = sum -(sum * discount1);
		}
		return sum;
	}



}
