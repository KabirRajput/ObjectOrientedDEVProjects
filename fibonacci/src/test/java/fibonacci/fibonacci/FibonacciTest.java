package fibonacci.fibonacci;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FibonacciTest {
	
	@Test
	public void test_Fibonnaci_of_0_gives_0_as_output() {
		
		Fibonacci fib = new Fibonacci();
		int num = 0;
		
		int result = fib.computeFib(num);
		
		assertEquals(0,result);
		
	}
	
	@Test
	public void test_Fibonnaci_of_1_gives_1_as_output() {
		
		Fibonacci fib = new Fibonacci();
		int num = 1;
		
		int result = fib.computeFib(num);
		
		assertEquals(1,result);
		
	}
	
	@Test
	public void test_Fibonnaci_of_2_gives_1_as_output() {
		
		Fibonacci fib = new Fibonacci();
		int num = 2;
		
		int result = fib.computeFib(num);
		
		assertEquals(1,result);
		
	}
	
	@Test
	public void test_Fibonnaci_of_10_gives_55_as_output() {
		
		Fibonacci fib = new Fibonacci();
		int num = 10;
		
		int result = fib.computeFib(num);
		
		assertEquals(55,result);
		
	}

}
