package com.fdmgroup.LegendAir.service;

import javax.servlet.ServletContext;

import com.fdmgroup.LegendAir.dal.UserDao;

public class LoginService {
	private UserDao userDao;
	
	public LoginService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String login(String username, String password) {
		return userDao.verifyCredentials(username, password);
	}
}
