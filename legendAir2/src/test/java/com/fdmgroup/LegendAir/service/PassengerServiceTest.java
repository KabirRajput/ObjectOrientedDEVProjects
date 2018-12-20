package com.fdmgroup.LegendAir.service;

import static org.mockito.Mockito.*;

import org.junit.Test;

import com.fdmgroup.LegendAir.dal.UserDao;
import com.fdmgroup.LegendAir.factory.PassengerFactory;

public class PassengerServiceTest {

	@Test
	public void given_username_userDao_returns_set_passengers() {
		String username = "Karthik";
		UserDao mockUd = mock(UserDao.class);
		PassengerFactory mockPf = mock(PassengerFactory.class);
		
		PassengerService ps = new PassengerService(mockUd, mockPf);
		ps.getPassengers(username);
		
		verify(mockUd).getPassengers(username);
	}
}
