package com.fdmgroup.LegendAir.controller;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.service.FlightService;

public class SearchFlightControllerTest {

	@Test
	public void doGet_forward_to_search_passing_origin_destination_flight_tickets_departureDate() throws ServletException, IOException {
		//arrange
		String origin = "Pudong, Shanghai, PVG";
		String destination = "HK, Hong Kong, HKG";
		String[] originData = {"Shanghai", "Pudong", "PVG"};
		String[] destinationData = {"Hong Kong", "HK", "HKG"};
		String departureDate = "25-Dec-2018";
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		ServletContext mockSr = mock(ServletContext.class);
		FlightService mockFs = mock(FlightService.class);
		List mockList = mock(List.class);
		RequestDispatcher mockRd = mock(RequestDispatcher.class);
		when(mockReq.getParameter("origin")).thenReturn(origin);
		when(mockReq.getParameter("destination")).thenReturn(destination);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockSr);
		when(mockSession.getServletContext()).thenReturn(mockSr);
		when(mockSr.getAttribute("flightService")).thenReturn(mockFs);
		when(mockReq.getRequestDispatcher("/WEB-INF/views/search.jsp")).thenReturn(mockRd);
		InOrder order = inOrder(mockReq, mockRes, mockSession, mockSr, mockFs, mockRd);
		
		//act
		SearchFlightController sfc = new SearchFlightController();
		sfc.doGet(mockReq, mockRes);
		
		//assert
		order.verify(mockReq).getParameter("origin");
		order.verify(mockReq).getParameter("destination");
		order.verify(mockReq).getParameter("departureDate");
		order.verify(mockReq).getSession();
		order.verify(mockSession).getServletContext();
		order.verify(mockSr).getAttribute("flightService");
//		order.verify(mockFs.getFlightTickets(originData[2], destinationData[2], departureDate));
//		order.verify(mockReq).setAttribute("originCity", originData[0]);
//		order.verify(mockReq).setAttribute("originAirport", originData[1]);
//		order.verify(mockReq).setAttribute("originIATAId", originData[2]);
//		order.verify(mockReq).setAttribute("destinationCity", destinationData[0]);
//		order.verify(mockReq).setAttribute("destinationAirport", destinationData[1]);
//		order.verify(mockReq).setAttribute("destinationIATAId", destinationData[2]);
		order.verify(mockReq).setAttribute("departureDate", departureDate);
		order.verify(mockReq).setAttribute("flightTickets", mockList);
//		order.verify(mockReq).getRequestDispatcher("WEB-INF/views/register.jsp");
//		order.verify(mockRd).forward(mockReq, mockRes);
//		String origin = "Shanghai, Pudong, PVG";
//		String destination = "Hong Kong, HK, HKG";
//		String[] originData = {"Shanghai", "Pudong", "PVG"};
//		String[] destinationData = {"Hong Kong", "HK", "HKG"};
//		String departureDate = "25-Dec-2018";
//		HttpServletRequest mockReq = mock(HttpServletRequest.class);
//		HttpServletResponse mockRes = mock(HttpServletResponse.class);
//		HttpSession mockSession = mock(HttpSession.class);
//		ServletContext mockSr = mock(ServletContext.class);
//		FlightService mockFs = mock(FlightService.class);
//		List mockList = mock(List.class);
//		RequestDispatcher mockRd = mock(RequestDispatcher.class);
//		when(mockReq.getParameter("origin")).thenReturn(origin);
//		when(mockReq.getParameter("destination")).thenReturn(destination);
//		when(mockReq.getParameter("departureDate")).thenReturn(departureDate);
//		when(mockReq.getSession()).thenReturn(mockSession);
//		when(mockSession.getServletContext()).thenReturn(mockSr);
//		when(mockSr.getAttribute("flightService")).thenReturn(mockFs);
//		when(mockFs.getFlightTickets(originData[2], destinationData[2], departureDate)).thenReturn(mockList);
//		when(mockReq.getRequestDispatcher("/WEB-INF/views/search.jsp")).thenReturn(mockRd);
//		InOrder order = inOrder(mockReq, mockSession, mockSr, mockSession, mockFs);
//		
//		SearchFlightController sfc = new SearchFlightController();
//		sfc.doGet(mockReq, mockRes);
//		
//		order.verify(mockReq.getParameter("origin"));
//		order.verify(mockReq.getParameter("destination"));
//		order.verify(mockReq.getParameter("departureDate"));
//		order.verify(mockReq.getSession());
//		order.verify(mockSession.getServletContext());
//		order.verify(mockSr.getAttribute("flightService"));
//		order.verify(mockFs.getFlightTickets(originData[2], destinationData[2], departureDate));
//		order.verify(mockReq.setAttribute("originCity", originData));
//		req.setAttribute("originAirport", originData[1]);
//		req.setAttribute("originIATAId", originData[2]);
//		req.setAttribute("destinationCity", destinationData[0]);
//		req.setAttribute("destinationAirport", destinationData[1]);
//		req.setAttribute("destinationIATAId", destinationData[2]);
//		req.setAttribute("departureDate", departureDate);
//		req.setAttribute("flightTickets", mockList);
		
	}
}
