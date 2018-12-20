package com.fdmgroup.LegendAir.service;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;

import com.fdmgroup.LegendAir.dal.FlightDao;


public class FlightServiceTest {

	@Test
	public void given_origin_destination_departureDate_list_is_returned_from_dao() {
		String originIATAId = "PVG";
		String destinationIATAId = "MXP";
		String departureDate = "23-Dec-18";
		FlightDao mockFd = mock(FlightDao.class);
		List mockList= mock(List.class);
		when(mockFd.getFlightTicketsByIdDate(originIATAId, destinationIATAId, departureDate)).thenReturn(mockList);
		
		FlightService fs = new FlightService(mockFd);
		fs.getFlightTickets(originIATAId, destinationIATAId, departureDate);
		
		verify(mockFd.getFlightTicketsByIdDate(originIATAId, destinationIATAId, departureDate));
	}
}
