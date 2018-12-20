package com.fdmgroup.LegendAir.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.LegendAir.service.AirportService;
import com.fdmgroup.LegendAir.service.FlightService;

public class SearchFlightController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String origin = req.getParameter("origin");
		String[] originData = origin.split(", ");
		String destination = req.getParameter("destination");
		String[] destinationData = destination.split(", ");
		String departureDate = req.getParameter("departureDate");
		HttpSession session = req.getSession();
		ServletContext servletContext = session.getServletContext();
		FlightService flightService = (FlightService) servletContext.getAttribute("flightService");
		List<Object[]> flightTickets = flightService.getFlightTickets(originData[2], destinationData[2], departureDate);
		session.setAttribute("originCity", originData[0]);
		session.setAttribute("originAirport", originData[1]);
		session.setAttribute("originIATAId", originData[2]);
		session.setAttribute("destinationCity", destinationData[0]);
		session.setAttribute("destinationAirport", destinationData[1]);
		session.setAttribute("destinationIATAId", destinationData[2]);
		session.setAttribute("departureDate", departureDate);
		session.setAttribute("flightTickets", flightTickets);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/search.jsp");
		rd.forward(req, resp);
	}
}
