package com.fdmgroup.legendwealth.service;

import org.junit.Test;

import com.fdmgroup.legendwealth.dal.BrokerDao;

import static org.mockito.Mockito.*;


public class LoginServiceTest {

	@Test
	public void given_username_and_password_called_brokerDao_getBrokerByUsernameAndPassword () {
		String username = "username";
		String password = "password";
		
		BrokerDao mockBrokerDao = mock(BrokerDao.class);
		
		LoginService loginService = new LoginService(mockBrokerDao);
		loginService.login(username, password);
		
		verify(mockBrokerDao).getBrokerByUsernameAndPassword(username, password);
		
	}
}
