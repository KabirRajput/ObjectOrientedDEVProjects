package fibonacci.fibonacci;
public class Fibonacci {

	public int computeFib(int num) {	
		
		/*
		Logic behind thinking process:
		With recursion, I didn't need to define any variables since the method 
		would simply call itself repeatedly until the base condition.
		For the loop, I decided to use a variable sumOfPreviousTwo to get the sum of the first 
		2 numbers and then iterate over the numbers as it can be seen from the loop. 
		Finally, I returned num1, since returning num2/ sumOfPreviousTwo would give the next number 
		in the sequence.
		*/
		
		int num1 = 0;
		int num2 = 1;
		int sumOfPreviousTwo =0;
		
		for (int i=1;i<=num;i++)
		{
			sumOfPreviousTwo  = num1 + num2;    
			num1 = num2;
			num2 = sumOfPreviousTwo;
		}
		return num1; 
		

	}

}
