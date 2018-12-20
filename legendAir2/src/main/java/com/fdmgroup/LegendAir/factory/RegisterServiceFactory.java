package com.fdmgroup.LegendAir.factory;

import com.fdmgroup.LegendAir.dal.UserDao;
import com.fdmgroup.LegendAir.service.RegisterService;

public class RegisterServiceFactory {

	public RegisterService getRegisterFactory(UserDao userDao) {
		return new RegisterService(userDao);
	}
}
