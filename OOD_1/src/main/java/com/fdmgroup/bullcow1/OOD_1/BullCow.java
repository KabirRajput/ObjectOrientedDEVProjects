package com.fdmgroup.bullcow1.OOD_1;

public class BullCow {

	private String secretNumber;

	public BullCow(String i) {
		this.secretNumber=i;
	}

	public BullCow(SecretNumberGenerator secretNumGen, int digits) {
		this.secretNumber =secretNumGen.generate(digits);
	}

	public int getCows(String number) {
		int cowCounter=0;
		if(number.length()!=secretNumber.length())
			return -1;
		if(number.length()==1)
		{
			return 0;
		}
		for(int i=0; i< number.length();i++)
			for(int j=0; j< secretNumber.length();j++)
				if(number.charAt(i)==secretNumber.charAt(j) && i!=j)
					cowCounter++;
		return cowCounter;
	}

	public int getBulls(String number) {
		if(number.length()!=secretNumber.length())
			return -1;		
		int bullCounter=0;
		for(int i=0; i<number.length(); i++) {
			if(number.charAt(i)== secretNumber.charAt(i)) 
				bullCounter++;
		}
		return bullCounter;

	}

}
