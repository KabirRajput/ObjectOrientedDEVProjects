package com.fdmgroup.LegendAir.controller;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.service.AirportService;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AirportControllerTest {
	@Test
	public void request_to_do_post_return_json_list_of_airports() throws ServletException, IOException {
		String city = "Milan";
		String airportsJSON = "Malpensa";
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpSession mockSession = mock(HttpSession.class);
		ServletContext mockSc = mock(ServletContext.class);
		AirportService mockAs = mock(AirportService.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		PrintWriter mockPw = mock(PrintWriter.class);
		when(mockReq.getParameter("city")).thenReturn(city);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockSc);
		when(mockSc.getAttribute("airportService")).thenReturn(mockAs);
		when(mockAs.getAirportsInCity(city)).thenReturn(airportsJSON);
		when(mockRes.getWriter()).thenReturn(mockPw);
		InOrder order = inOrder(mockReq, mockSession, mockSc, mockAs, mockRes, mockPw);
		
		AirportController ac = new AirportController();
		ac.doPost(mockReq, mockRes);
		
		order.verify(mockReq).getParameter("city");
		order.verify(mockReq).getSession();
		order.verify(mockSession).getServletContext();
		order.verify(mockSc).getAttribute("airportService");
		order.verify(mockAs).getAirportsInCity(city);
		order.verify(mockRes).setContentType("application/json");
		order.verify(mockRes).setCharacterEncoding("UTF-8");
		order.verify(mockRes).getWriter();
		order.verify(mockPw).write(airportsJSON);
		
	}
}
