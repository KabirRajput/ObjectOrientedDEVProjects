package com.fdmgroup.LegendAir.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fdmgroup.LegendAir.entity.User;

public class UserFactoryTest {

	@Test
	public void userFactory_returns_user() {
		//arrange
		UserFactory uf = new UserFactory();
				
		//act
		User user = uf.getUser();
		
		//assert
		assertEquals(User.class, user.getClass());
	}
}
