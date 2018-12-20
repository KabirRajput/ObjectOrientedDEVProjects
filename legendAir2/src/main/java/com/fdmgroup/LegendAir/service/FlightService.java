package com.fdmgroup.LegendAir.service;

import java.util.List;

import com.fdmgroup.LegendAir.dal.FlightDao;

public class FlightService {
	private FlightDao flightDao;
	
	public FlightService(FlightDao flightDao) {
		this.flightDao = flightDao;
	}
	
	public List<Object[]> getFlightTickets(String originIATAId, String destinationIATAId, String departureDate) {
		return flightDao.getFlightTicketsByIdDate(originIATAId, destinationIATAId, departureDate);
	}
}
