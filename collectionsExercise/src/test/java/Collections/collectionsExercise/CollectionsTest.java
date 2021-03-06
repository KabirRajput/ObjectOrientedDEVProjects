package Collections.collectionsExercise;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

public class CollectionsTest {

	@Test
	public void test1_Read_1_Name_And_Store_To_Set() {

		//arrange
		People people = new People();	
		
		//act
		Set<String> result = people.addNames("Kabir");
		
		//assert
		assertEquals(1, result.size());
		
	}
	
	@Test
	public void test2_Read_2_Names_And_Store_To_Set() {

		//arrange
		People people = new People();	
		
		//act
		Set<String> result = people.addNames("Kabir","Snow");
		
		//assert
		assertEquals(2, result.size());
		
	}

	@Test
	public void test2_Read_3_Names_And_Store_To_Set() {

		//arrange
		People people = new People();	
		
		//act
		Set<String> result = people.addNames("Kabir","Snow","Drogon");
		
		//assert
		assertEquals(3, result.size());
		
	}
}
