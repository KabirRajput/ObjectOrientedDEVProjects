package Collections.collectionsExercise;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class HelloTest {

	@Test
	public void test1_print_occurance_of_A() {
		
		Hello hello = new Hello();
		
		String s = "aasjjikkk";
		
		hello.addString(s);
		int result = hello.returnString('a');
		assertEquals(2, result);
		
	}
}
