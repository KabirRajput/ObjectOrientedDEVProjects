package com.fdmgroup.LegendAir.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.LegendAir.service.PassengerService;

public class PassengerController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		HttpSession session = req.getSession();
		ServletContext servletContext = session.getServletContext();
		String name = (String) req.getAttribute("name");
		String surname = (String) req.getAttribute("surname");
		String mobile = (String) req.getAttribute("mobile");
		String passport = (String) req.getAttribute("passport");
		String username = (String) session.getAttribute("active_username");
		PassengerService passengerService = (PassengerService) servletContext.getAttribute("passengerService");
		passengerService.add(username, name, surname, mobile, passport);
	}
}
