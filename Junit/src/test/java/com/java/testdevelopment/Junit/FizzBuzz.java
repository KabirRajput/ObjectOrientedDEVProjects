package com.java.testdevelopment.Junit;

public class FizzBuzz {

	public String evaluate(int i) {
		
		if(i%5==0 && i%3==0)
			return "FIZZBUZZ";
		if(i%3==0)
			return "FIZZ";
		if(i%5==0)
			return "BUZZ";
		return Integer.toString(i);
	}

}
