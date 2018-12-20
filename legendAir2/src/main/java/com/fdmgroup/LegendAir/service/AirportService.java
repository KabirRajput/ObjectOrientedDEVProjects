package com.fdmgroup.LegendAir.service;

import java.util.List;

import com.fdmgroup.LegendAir.dal.AirportDao;
import com.fdmgroup.LegendAir.wrapper.GsonWrapper;
import com.google.gson.Gson;

public class AirportService {
	private AirportDao airportDao;
	private GsonWrapper gson;
	
	public AirportService(AirportDao airportDao, GsonWrapper gson) {
		this.airportDao = airportDao;
		this.gson = gson;
	}

	public String getAirportsInCity(String city) {
		List<Object[]> airportList = airportDao.getByCity(city);
		String airportJSON = gson.toJson(airportList);
		return airportJSON;
	}
}
