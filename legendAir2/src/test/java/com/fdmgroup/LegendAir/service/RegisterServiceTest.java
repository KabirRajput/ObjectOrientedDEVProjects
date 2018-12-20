package com.fdmgroup.LegendAir.service;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.dal.UserDao;
import com.fdmgroup.LegendAir.entity.User;
import com.fdmgroup.LegendAir.factory.UserFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterServiceTest {

	@Test
	public void register_new_username() {
		//arrange
		String username = "andrew";
		String password = "pass";
		String confirmPassword = "pass";
		UserDao mockUd = mock(UserDao.class);
		UserFactory mockUf = mock(UserFactory.class);
		InOrder order = inOrder(mockUd, mockUf);
		
		//act
		RegisterService rs = new RegisterService(mockUd);
		String result = rs.register(username, password , confirmPassword);
		
		//assert
		order.verify(mockUd).getByUsername(username);
		assertEquals("Registration successful", result);
	}
	
	@Test
	public void register_new_existing_username() {
		//arrange
		String username = "andrew";
		String password = "pass";
		String confirmPassword = "pass";
		UserDao mockUd = mock(UserDao.class);
		UserFactory mockUf = mock(UserFactory.class);
		User mockUser = mock(User.class);
		when(mockUd.getByUsername(username)).thenReturn(mockUser);
		InOrder order = inOrder(mockUd, mockUf);
		
		//act
		RegisterService rs = new RegisterService(mockUd);
		String result = rs.register(username, password , confirmPassword);
		
		//assert
		order.verify(mockUd).getByUsername(username);
		assertEquals("Username already exists", result);
	}
	
	@Test
	public void register_new_existing_username_passwords_do_not_match() {
		//arrange
		String username = "andrew";
		String password = "pass";
		String confirmPassword = "asd";
		UserDao mockUd = mock(UserDao.class);
		UserFactory mockUf = mock(UserFactory.class);
		InOrder order = inOrder(mockUd, mockUf);
		
		//act
		RegisterService rs = new RegisterService(mockUd);
		String result = rs.register(username, password , confirmPassword);
		
		//assert
		order.verify(mockUd).getByUsername(username);
		assertEquals("Passwords do not match", result);
	}
}
