package com.fdmgroup.LegendAir.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.mockito.InOrder;

public class IndexControllerTest {

	@Test
	public void when_get_request_to_index_display_index_jsp() throws ServletException, IOException {
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		RequestDispatcher mockRd = mock(RequestDispatcher.class);
		when(mockReq.getRequestDispatcher("WEB-INF/views/index.jsp")).thenReturn(mockRd);
		InOrder order = inOrder(mockReq, mockRd);
		
		IndexController ic = new IndexController();
		ic.doGet(mockReq, mockRes);
		
		order.verify(mockReq).getRequestDispatcher("WEB-INF/views/index.jsp");
		order.verify(mockRd).forward(mockReq, mockRes);
	}
}
