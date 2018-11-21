package Ex5Mockito.Mock;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

import org.junit.Test;

public class CatalogueTest {

	@Test
	public void test_GetAllBooks_ReturnsEmptyBookList_IfNoBooksAreInTheCatalogue(){

		//arrange
		Catalogue catalogue = new Catalogue();

		//act
		int returnedBooks = catalogue.getAllBooks();

		//assert
		assertEquals(0,returnedBooks);
	}

	@Test
	public void test_GetAllBooks_CallsReadAllMethodOfReadItemCommand_WhenCalled(){
		
		//arrange
		ReadItemCommand mockReadItemCommand =  mock(ReadItemCommand.class);
		Catalogue catalogue = new Catalogue(mockReadItemCommand);
		
		//act
		catalogue.getAllBooks();
		
		//assert
		verify(mockReadItemCommand).readAll();
		
	}

}
