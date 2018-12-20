package com.fdmgroup.LegendAir.service;

import java.util.List;

import com.fdmgroup.LegendAir.dal.UserDao;
import com.fdmgroup.LegendAir.entity.Passenger;
import com.fdmgroup.LegendAir.factory.PassengerFactory;

public class PassengerService {
	UserDao userDao;
	PassengerFactory passengerFactory;
	
	public PassengerService(UserDao userDao, PassengerFactory passengerFactory) {
		this.userDao = userDao;
		this.passengerFactory = passengerFactory;
	}
	
	public List<String[]> getPassengers(String username) {
		return userDao.getPassengers(username);
	}

	public void add(String username, String name, String surname, String mobile, String passport) {
		Passenger passenger = passengerFactory.getPassenger(name, surname, mobile, passport);
		userDao.addPassenger(username, passenger);
	}
}
