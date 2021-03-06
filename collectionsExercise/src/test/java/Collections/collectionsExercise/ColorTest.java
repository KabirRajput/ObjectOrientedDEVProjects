package Collections.collectionsExercise;

import java.awt.Color;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class ColorTest {

	@Test
	public void test1_return_HashMap() {
		
		//arrange
		HashMap<String, Color> colorMap = mock(HashMap.class);
		when(colorMap.get("RED")).thenReturn(Color.RED);
		
		ColourTest colourTest = new ColourTest();
		
		//act
		HashMap<String, Color> hs = colourTest.putColor(colorMap);
		
		verify(colorMap).get("RED");
		
		assertEquals(Color.RED, hs.get("RED"));
	}
}
