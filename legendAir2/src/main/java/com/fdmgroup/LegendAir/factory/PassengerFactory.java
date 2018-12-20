package com.fdmgroup.LegendAir.factory;

import com.fdmgroup.LegendAir.entity.Passenger;

public class PassengerFactory {

	public Passenger getPassenger(String name, String surname, String mobile, String passport) {
		Passenger passenger = new Passenger(name, surname, mobile, passport);
		return passenger;
	}
}
