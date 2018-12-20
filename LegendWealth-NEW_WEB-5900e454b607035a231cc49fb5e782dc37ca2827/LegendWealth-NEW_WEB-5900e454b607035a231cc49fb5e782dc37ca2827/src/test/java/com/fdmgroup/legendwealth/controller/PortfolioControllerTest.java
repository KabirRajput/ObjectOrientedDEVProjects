package com.fdmgroup.legendwealth.controller;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.legendwealth.service.PortfolioService;

public class PortfolioControllerTest {
	@Test
	public void when_portfolio_is_requested_by_GET_then_forward_to_portfolio_jsp() throws ServletException, IOException {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);
		PortfolioService mockPortfolioSummarySerivce = mock(PortfolioService.class);
		ServletContext mockServletContext = mock(ServletContext.class);

		String stringId = "123";
		
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockRequest.getParameter("id")).thenReturn(stringId);
		when(mockSession.getServletContext()).thenReturn(mockServletContext);
		when(mockServletContext.getAttribute("portfolioSummarySerivce")).thenReturn(mockPortfolioSummarySerivce);
		when(mockRequest.getRequestDispatcher("WEB-INF/views/portfolio.jsp")).thenReturn(mockRD);
		
		PortfolioController portfolioController = new PortfolioController();
		portfolioController.doGet(mockRequest, mockResponse);
		
		InOrder order = inOrder(mockRequest, mockRD);
		
		order.verify(mockRequest).getRequestDispatcher("WEB-INF/views/portfolio.jsp");
		order.verify(mockRD).forward(mockRequest, mockResponse);
	}
}
