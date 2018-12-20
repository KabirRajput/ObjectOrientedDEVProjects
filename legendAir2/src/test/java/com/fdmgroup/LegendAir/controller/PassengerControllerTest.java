package com.fdmgroup.LegendAir.controller;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.service.PassengerService;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PassengerControllerTest {

	@Test
	public void when_POST_request_passenger_service_persist_to_db() throws ServletException, IOException {
		String name = "karthik";
		String surname = "asokan";
		String mobile = "666-666-666";
		String passport = "HK123456";
		String username = "karthik123";
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		ServletContext mockSc = mock(ServletContext.class);
		PassengerService mockPs = mock(PassengerService.class);
		InOrder order = inOrder(mockReq, mockSession, mockSc, mockPs);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockSc);
		when(mockReq.getParameter("name")).thenReturn(name);
		when(mockReq.getParameter("surname")).thenReturn(surname);
		when(mockReq.getParameter("mobile")).thenReturn(mobile);
		when(mockReq.getParameter("passport")).thenReturn(passport);
		when(mockSession.getAttribute("active_username")).thenReturn(username);
		when(mockSc.getAttribute("passengerService")).thenReturn(mockPs);
		
		PassengerController pc = new PassengerController();
		pc.doPost(mockReq, mockRes);
		
		order.verify(mockReq).getSession();
		order.verify(mockSession).getServletContext();
		order.verify(mockReq).getAttribute("name");
		order.verify(mockReq).getAttribute("surname");
		order.verify(mockReq).getAttribute("mobile");
		order.verify(mockReq).getAttribute("passport");
		order.verify(mockSession).getAttribute("active_username");
		order.verify(mockSc).getAttribute("passengerService");
//		order.verify(mockPs).add(username, name, surname, mobile, passport);
	}
}
