package com.fdmgroup.LegendAir.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

import java.io.IOException;

import java.util.*;
import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.entity.Passenger;
import com.fdmgroup.LegendAir.service.PassengerService;

public class BookControllerTest {

	@Test
	public void when_doGet_request_to_book_user_is_redirected_to_login_page_when_not_logged_in() throws ServletException, IOException {
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockReq.getSession()).thenReturn(mockSession);
		InOrder order = inOrder(mockReq, mockRes, mockSession);
		
		BookController bc = new BookController();
		bc.doGet(mockReq, mockRes);
		
		order.verify(mockReq).getSession();
		order.verify(mockSession).getAttribute("active_username");
		order.verify(mockReq).getContextPath();
		order.verify(mockRes).sendRedirect(mockReq.getContextPath() + "/login");
	}
	
	@Test
	public void when_user_logged_doGet_forward_to_booking_page() throws ServletException, IOException {
		String activeUsername = "Andrew";
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		ServletContext mockSc = mock(ServletContext.class);
		RequestDispatcher mockRd = mock(RequestDispatcher.class);
		PassengerService mockPs = mock(PassengerService.class);
		List mockList = mock(List.class);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("active_username")).thenReturn(activeUsername);
		when(mockReq.getRequestDispatcher("WEB-INF/views/book.jsp")).thenReturn(mockRd);
		when(mockSession.getServletContext()).thenReturn(mockSc);
		when(mockSc.getAttribute("passengerService")).thenReturn(mockPs);
		when(mockPs.getPassengers(activeUsername)).thenReturn(mockList);
		InOrder order = inOrder(mockReq, mockRes, mockRd, mockSession); 
		
		BookController bc = new BookController();
		bc.doGet(mockReq, mockRes);
		
		order.verify(mockReq).getSession();
		order.verify(mockSession).getAttribute("active_username");
		order.verify(mockReq).getRequestDispatcher("WEB-INF/views/book.jsp");
		order.verify(mockRd).forward(mockReq, mockRes);
		
	}
	
	@Test
	public void when_user_logged_doGet_forward_to_booking_page_and_set_attribute() throws ServletException, IOException {
		String activeUsername = "Andrew";
		String flightTicketId = "1";
		String departureTime = "1:50";
		String	arrivalTime = "2:50";
		String price = "788.00";
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		ServletContext mockSc = mock(ServletContext.class);
		RequestDispatcher mockRd = mock(RequestDispatcher.class);
		PassengerService mockPs = mock(PassengerService.class);
		List mockList = mock(List.class);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("active_username")).thenReturn(activeUsername);
		when(mockReq.getParameter("flightTicketId")).thenReturn(flightTicketId);
		when(mockReq.getParameter("departureTime")).thenReturn(departureTime);
		when(mockReq.getParameter("arrivalTime")).thenReturn(arrivalTime);
		when(mockReq.getParameter("price")).thenReturn(price);
		when(mockReq.getRequestDispatcher("WEB-INF/views/book.jsp")).thenReturn(mockRd);
		when(mockSession.getServletContext()).thenReturn(mockSc);
		when(mockSc.getAttribute("passengerService")).thenReturn(mockPs);
		when(mockPs.getPassengers(activeUsername)).thenReturn(mockList);
		InOrder order = inOrder(mockReq, mockRes, mockRd, mockSession); 
		
		BookController bc = new BookController();
		bc.doGet(mockReq, mockRes);
		
		order.verify(mockReq).getSession();
		order.verify(mockSession).getAttribute("active_username");
		order.verify(mockReq).getParameter("flightTicketId");
		order.verify(mockReq).getParameter("departureTime");
		order.verify(mockReq).getParameter("arrivalTime");
		order.verify(mockReq).getParameter("price");
		order.verify(mockReq).setAttribute("flightTicketId", flightTicketId);
		order.verify(mockReq).setAttribute("departureTime", departureTime);
		order.verify(mockReq).setAttribute("arrivalTime", arrivalTime);
		order.verify(mockReq).setAttribute("price", price);
		order.verify(mockReq).getRequestDispatcher("WEB-INF/views/book.jsp");
		order.verify(mockRd).forward(mockReq, mockRes);
	}
	
	@Test
	public void book_page_displays_user_passenger() throws ServletException, IOException {
		String activeUsername = "Andrew";
		String flightTicketId = "1";
		String departureTime = "1:50";
		String	arrivalTime = "2:50";
		String price = "788.00";
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		ServletContext mockSc = mock(ServletContext.class);
		PassengerService mockPs = mock(PassengerService.class);
		List mockList = mock(List.class);
		RequestDispatcher mockRd = mock(RequestDispatcher.class);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockSc);
		when(mockSc.getAttribute("passengerService")).thenReturn(mockPs);
		when(mockSession.getAttribute("active_username")).thenReturn(activeUsername);
		when(mockReq.getParameter("flightTicketId")).thenReturn(flightTicketId);
		when(mockReq.getParameter("departureTime")).thenReturn(departureTime);
		when(mockReq.getParameter("arrivalTime")).thenReturn(arrivalTime);
		when(mockReq.getParameter("price")).thenReturn(price);
		when(mockReq.getRequestDispatcher("WEB-INF/views/book.jsp")).thenReturn(mockRd);
		when(mockSession.getAttribute("passengerService")).thenReturn(mockPs);
		when(mockPs.getPassengers(activeUsername)).thenReturn(mockList);
		InOrder order = inOrder(mockReq, mockRes, mockRd, mockSession, mockPs); 
		
		BookController bc = new BookController();
		bc.doGet(mockReq, mockRes);
		
		order.verify(mockReq).getSession();
		order.verify(mockSession).getAttribute("active_username");
		order.verify(mockReq).getParameter("flightTicketId");
		order.verify(mockReq).getParameter("departureTime");
		order.verify(mockReq).getParameter("arrivalTime");
		order.verify(mockReq).getParameter("price");
		order.verify(mockPs).getPassengers(activeUsername);
		order.verify(mockReq).setAttribute("flightTicketId", flightTicketId);
		order.verify(mockReq).setAttribute("departureTime", departureTime);
		order.verify(mockReq).setAttribute("arrivalTime", arrivalTime);
		order.verify(mockReq).setAttribute("price", price);
		order.verify(mockReq).setAttribute("passengersData", mockList);
		order.verify(mockReq).getRequestDispatcher("WEB-INF/views/book.jsp");
		order.verify(mockRd).forward(mockReq, mockRes);
	}
}
