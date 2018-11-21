package currencyProject.currencyProject;

import static org.junit.Assert.assertEquals;

import javax.swing.text.Document;

import org.junit.Test;

public class ConverterTest {
	
	@Test
	public void test_rate_return_() {
		
		FileConverter conv = new FileConverter("H:/Week5/DailyCurrencyFile.xml");
		
		Double result = conv.getRate("USD");
		
		assertEquals(1.1305,result,0.000);
		
		
		
	}

}
