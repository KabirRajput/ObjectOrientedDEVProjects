package com.fdmgroup.legendwealth.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

import java.io.IOException;

import org.mockito.*;

import org.junit.Test;

public class LogoutControllerTest {
	@Test
	public void when_logout_is_requested_by_GET_invalidate_the_session() throws ServletException, IOException {
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);

		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockRequest.getRequestDispatcher("WEB-INF/views/index.jsp")).thenReturn(mockRD);

		LogoutController logoutContoller = new LogoutController();
		logoutContoller.doGet(mockRequest, mockResponse);
		
		InOrder order = inOrder(mockSession, mockRD, mockRequest);
		
		order.verify(mockSession).invalidate();
		order.verify(mockRequest).setAttribute("logout_success", true);
		order.verify(mockRD).forward(mockRequest, mockResponse);
		
		
	}

}
