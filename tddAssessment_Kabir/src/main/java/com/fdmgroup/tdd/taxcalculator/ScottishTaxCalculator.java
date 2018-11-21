package com.fdmgroup.tdd.taxcalculator;

public class ScottishTaxCalculator implements TaxCalculationService {

	public double getTaxRate(double income) {
		if(income<=11500)
			return 0;
		if(income>=11501 && income<=13850)
			return 19;
		if(income>=13851 && income<=24000)
			return 20;
		if(income>=24001 && income<=44273)
			return 21;
		if(income>=44274 && income<=150000)
			return 41;
		if(income>=150001)
			return 46;
		return -1;
	}
	

}
