package com.fdmgroup.LegendAir.service;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.dal.UserDao;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

public class LoginServiceTest {

	@Test
	public void login_with_existing_username() {
		String username = "andrew";
		String password = "password";
		UserDao mockUd = mock(UserDao.class);
		LoginService ls = new LoginService(mockUd);
		InOrder order = inOrder(mockUd);
		
		ls.login(username, password);
		
		order.verify(mockUd).verifyCredentials(username, password);
	}
}
