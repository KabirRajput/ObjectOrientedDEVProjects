package UserReg.userReg;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class UserRegControllerTest {
	
	@Test
	public void addUser_should_call_writeUser_on_persistanceObject() {
		
		//arrange 
		UserPersistance mockPersistance = mock(UserPersistance.class);
		UserRegController cont = new UserRegController(mockPersistance);
		User bob = new User();
		
		//act
		cont.addUser(bob);
		
		//assert
		verify(mockPersistance).writeUser(bob);
	}
	
	@Test
	public void getUser_should_call_readUser_on_persistanceObject() {
		
		//arrange 
		UserPersistance mockPersistance = mock(UserPersistance.class);
		UserRegController cont = new UserRegController(mockPersistance);
		
		//act
		cont.getUser(1);
		
		//assert
		verify(mockPersistance).readUser(1);
	}

	@Test
	public void getUser_should_return_User_that_was_returned_by_readUser() {
		
		//arrange 
		UserPersistance mockPersistance = mock(UserPersistance.class);
		UserRegController cont = new UserRegController(mockPersistance);
		User rob = new User();
		when(mockPersistance.readUser(1)).thenReturn(rob);
		
		//act
		User returnedUser = cont.getUser(1);
		
		//assert
		assertEquals(rob, returnedUser);
	}
}
