package com.fdmgroup.bullcow1.OOD_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

public class SecretNumberGeneratorTest {

	//Phase2
	@Test
	public void test1_Random_generatorTesting_for_one_digit(){

		//arrange
		SecretNumberGenerator secretNumGen = new SecretNumberGenerator();

		//act
		String secretNumber = secretNumGen.generate(1);


		//assert
		assertEquals(1, secretNumber.length());	
	}

	@Test
	public void test2_Random_generatorTesting_for_two_digit(){

		//arrange
		SecretNumberGenerator secretNumGen = new SecretNumberGenerator();

		//act
		String secretNumber = secretNumGen.generate(2);


		//assert
		assertEquals(2, secretNumber.length());
	}
	
	@Test
	public void test3_Random_generatorTesting_for_three_digit(){

		//arrange
		SecretNumberGenerator secretNumGen = new SecretNumberGenerator();

		//act
		String secretNumber = secretNumGen.generate(3);


		//assert
		assertEquals(3, secretNumber.length());
	}
	
	@Test
	public void test4_Random_generatorTesting_for_four_digit(){

		//arrange
		SecretNumberGenerator secretNumGen = new SecretNumberGenerator();

		//act
		String secretNumber = secretNumGen.generate(4);


		//assert
		assertEquals(4, secretNumber.length());
	}

	@Test
	public void test5_Random_generatorTesting_for_different_digits(){

		//arrange
		SecretNumberGenerator secretNumGen = new SecretNumberGenerator();

		//act
		String secretNumber = secretNumGen.generate(4);
		boolean isDiff = true;
		for(int i=0;i<4;i++) {
			for(int j=0; j<4;j++) {
				if(secretNumber.charAt(i)==secretNumber.charAt(j)&& i != j) {
					isDiff= false;
				}
			}
		}


		//assert
		assertTrue(isDiff);

	}

	@Test
	public void test6_1000_random_genTests(){

		//arrange
		SecretNumberGenerator secretNumGen = new SecretNumberGenerator();

		//act
		String secretNumber = secretNumGen.generate(4);
		boolean isDiff = true;
		for(int i=0;i<4;i++) {
			for(int j=0; j<4;j++) {
				if(secretNumber.charAt(i)==secretNumber.charAt(j)&& i != j) {
					isDiff= false;
				}
			}
		}
		
		//assert
				assertTrue(isDiff);
	}



}
