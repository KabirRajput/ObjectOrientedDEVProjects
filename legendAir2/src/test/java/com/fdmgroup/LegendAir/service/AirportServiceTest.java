package com.fdmgroup.LegendAir.service;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.dal.AirportDao;
import com.fdmgroup.LegendAir.wrapper.GsonWrapper;
import com.google.gson.Gson;

import static org.mockito.Mockito.*;

import java.util.List;

public class AirportServiceTest {

	@Test
	public void when_city_is_passed_db_is_checked_and_list_is_returned_then_list_converted_to_json() {
		String city = "Milan";
		AirportDao mockAd = mock(AirportDao.class);
		GsonWrapper mockGson = mock(GsonWrapper.class);
		List mockList = mock(List.class);
		when(mockAd.getByCity(city)).thenReturn(mockList);
		InOrder order = inOrder(mockAd, mockGson);
		
		AirportService as = new AirportService(mockAd, mockGson);
		as.getAirportsInCity(city);
		
		order.verify(mockAd).getByCity(city);
		order.verify(mockGson).toJson(mockList);
	}
}
