package generics.generic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PairTest {


	@Test 
	public void Test_getter() {

		PairGeneric<String,String> pairGeneric = new PairGeneric<String,String >("Kabir","Chris");
		String result = pairGeneric.getA();
		assertEquals("Kabir",result);

	}

	@Test 
	public void Test_CatalogueClass() {

		Book book1 = new Book(1);
		CatalogueGeneric<Book> catalogue = new CatalogueGeneric<Book>();
		catalogue.add(book1);
		Book result = catalogue.findT(1);
		assertEquals(book1,result);

	}
}
