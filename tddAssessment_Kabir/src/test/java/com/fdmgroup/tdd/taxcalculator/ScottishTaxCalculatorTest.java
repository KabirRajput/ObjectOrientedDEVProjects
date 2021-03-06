package com.fdmgroup.tdd.taxcalculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ScottishTaxCalculatorTest {

	@Test
	public void test_return_Tax_bracket_0_for_income_10000(){
		
		TaxCalculationService scotishCalculator = new ScottishTaxCalculator();
		double income = 10000.05;
		
		double result = scotishCalculator.getTaxRate(income);
		
		assertEquals(0, result, 0.00);
	}
	
	@Test
	public void test_return_Tax_bracket_19_for_income_12000(){
		
		TaxCalculationService scotishCalculator = new ScottishTaxCalculator();
		double income = 12000.05;
		
		double result = scotishCalculator.getTaxRate(income);
		
		assertEquals(19, result, 0.00);
	}
	
	@Test
	public void test_return_Tax_bracket_20_for_income_20000(){
		
		TaxCalculationService scotishCalculator = new ScottishTaxCalculator();
		double income = 20000.05;
		
		double result = scotishCalculator.getTaxRate(income);
		
		assertEquals(20, result, 0.00);
	}
	
	@Test
	public void test_return_Tax_bracket_21_for_income_40000(){
		
		TaxCalculationService scotishCalculator = new ScottishTaxCalculator();
		double income = 40000.05;
		
		double result = scotishCalculator.getTaxRate(income);
		
		assertEquals(21, result, 0.00);
	}
	
	@Test
	public void test_return_Tax_bracket_41_for_income_100000(){
		
		TaxCalculationService scotishCalculator = new ScottishTaxCalculator();
		double income = 100000.05;
		
		double result = scotishCalculator.getTaxRate(income);
		
		assertEquals(41, result, 0.00);
	}
	
	@Test
	public void test_return_Tax_bracket_46_for_income_200000(){
		
		TaxCalculationService scotishCalculator = new ScottishTaxCalculator();
		double income = 200000.05;
		
		double result = scotishCalculator.getTaxRate(income);
		
		assertEquals(46, result, 0.00);
	}
	
}
