package com.fdmgroup.LegendAir.service;

import javax.servlet.ServletContext;

import com.fdmgroup.LegendAir.dal.UserDao;
import com.fdmgroup.LegendAir.entity.User;
import com.fdmgroup.LegendAir.factory.UserFactory;

public class RegisterService {
	private UserDao ud;
	private UserFactory uf = new UserFactory();
	
	public RegisterService(UserDao ud) {
		this.ud = ud;
	}

	public String register(String username, String password, String confirmPassword) {
		User returnedUser = ud.getByUsername(username);
		User user = uf.getUser();
		user.setUsername(username);
		user.setPassword(password);
		
		if(!password.equals(confirmPassword)) {
			return "Passwords do not match";
		}
		
		if(returnedUser == null) {
			ud.add(user);
			return "Registration successful";
		}
		
		return "Username already exists";
	}
	
}
