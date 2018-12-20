package com.fdmgroup.LegendAir.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.LegendAir.entity.Passenger;
import com.fdmgroup.LegendAir.service.PassengerService;

public class BookController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String activeUsername = (String) session.getAttribute("active_username");
		
		if(activeUsername == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			String flightTicketId = req.getParameter("flightTicketId");
			String departureTime = req.getParameter("departureTime");
			String arrivalTime = req.getParameter("arrivalTime");
			String price = req.getParameter("price");
			ServletContext servletContext = session.getServletContext();
			PassengerService ps = (PassengerService) servletContext.getAttribute("passengerService");
			List<String[]> passengersData = ps.getPassengers(activeUsername);
			req.setAttribute("flightTicketId", flightTicketId);
			req.setAttribute("departureTime", departureTime);
			req.setAttribute("arrivalTime", arrivalTime);
			req.setAttribute("price", price);
			req.setAttribute("passengersData", passengersData);
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/book.jsp");
			rd.forward(req, resp);
		}
	}
}
