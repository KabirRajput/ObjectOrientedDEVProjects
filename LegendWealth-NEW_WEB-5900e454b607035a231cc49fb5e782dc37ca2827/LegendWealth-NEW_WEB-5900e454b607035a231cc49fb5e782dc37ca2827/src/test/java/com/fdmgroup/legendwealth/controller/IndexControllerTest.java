package com.fdmgroup.legendwealth.controller;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.legendwealth.entity.Broker;

public class IndexControllerTest {
	
	@Test
	public void when_index_is_requested_by_GET_user_has_not_login_then_forward_to_index_jsp() throws ServletException, IOException {
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);

		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockRequest.getRequestDispatcher("WEB-INF/views/index.jsp")).thenReturn(mockRD);
		
		IndexController indexController = new IndexController();
		indexController.doGet(mockRequest, mockResponse);
		
		InOrder order = inOrder(mockRequest, mockRD);
		
		order.verify(mockRequest).getRequestDispatcher("WEB-INF/views/index.jsp");
		order.verify(mockRD).forward(mockRequest, mockResponse);
		
	}
	
	@Test
	public void when_index_is_requested_by_GET_user_has_login_then_forward_to_home() throws ServletException, IOException {
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);
		Broker mockBroker = mock(Broker.class);

		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("active_broker")).thenReturn(mockBroker);
		when(mockRequest.getRequestDispatcher("home")).thenReturn(mockRD);
		
		IndexController indexController = new IndexController();
		indexController.doGet(mockRequest, mockResponse);
		
		InOrder order = inOrder(mockRequest, mockRD);
		
		order.verify(mockRequest).getRequestDispatcher("home");
		order.verify(mockRD).forward(mockRequest, mockResponse);
		
	}
}
