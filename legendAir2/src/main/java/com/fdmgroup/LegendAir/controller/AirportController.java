package com.fdmgroup.LegendAir.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.LegendAir.service.AirportService;

public class AirportController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String city = req.getParameter("city");
		HttpSession session = req.getSession();
		ServletContext servletContext = session.getServletContext();
		AirportService airportService = (AirportService) servletContext.getAttribute("airportService");
		String airportsJSON = airportService.getAirportsInCity(city);
		resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    PrintWriter printWriter = resp.getWriter();
	    printWriter.write(airportsJSON);
	}
}
