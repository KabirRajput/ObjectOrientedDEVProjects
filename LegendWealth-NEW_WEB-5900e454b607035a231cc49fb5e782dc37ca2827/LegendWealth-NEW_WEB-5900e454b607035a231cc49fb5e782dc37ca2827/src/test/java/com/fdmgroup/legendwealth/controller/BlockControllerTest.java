package com.fdmgroup.legendwealth.controller;

import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BlockControllerTest {

	@Test
	public void forward_to_blocked_jsp() throws ServletException, IOException {
		BlockedController blockedController = new BlockedController();
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);
		
		
		when(mockRequest.getRequestDispatcher("WEB-INF/views/blocked.jsp")).thenReturn(mockRD);
		
		blockedController.doPost(mockRequest, mockResponse);
		
		InOrder order = inOrder(mockRequest, mockRD);
		
		order.verify(mockRequest).getRequestDispatcher("WEB-INF/views/blocked.jsp");
		order.verify(mockRD).forward(mockRequest, mockResponse);
	}
}
