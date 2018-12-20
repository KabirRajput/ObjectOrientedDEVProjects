package com.fdmgroup.legendwealth.controller;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.legendwealth.dal.PortfolioDao;
import com.fdmgroup.legendwealth.dal.TradeDao;
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.service.DisplayService;
import com.fdmgroup.legendwealth.service.PortfolioService;


public class HomeControllerTest {

	@Test
	public void when_home_is_requested_by_POST_then_forward_to_home_jsp() throws ServletException, IOException {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);
		ServletContext mockServletContext = mock(ServletContext.class);
		List<Object[]> mockPortfolioList = mock(List.class);
		List<Object[]> mockCashInPortfolioList = mock(List.class);
		TradeDao mockTradeDao = mock(TradeDao.class);

		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockServletContext);
		when(mockServletContext.getAttribute("tradeDao")).thenReturn(mockTradeDao);
		when(mockTradeDao.getPortfolioList()).thenReturn(mockPortfolioList);
		when(mockTradeDao.getCashinPortfolio()).thenReturn(mockCashInPortfolioList);
		
		when(mockRequest.getRequestDispatcher("WEB-INF/views/home.jsp")).thenReturn(mockRD);
		
		HomeController homeController = new HomeController();
		homeController.doPost(mockRequest, mockResponse);
		
		InOrder order = inOrder(mockRequest, mockRD);
		
		order.verify(mockRequest).setAttribute("portfolioList", mockPortfolioList);
		order.verify(mockRequest).setAttribute("cashInPortfolioList", mockCashInPortfolioList);
		order.verify(mockRequest).getRequestDispatcher("WEB-INF/views/home.jsp");
		order.verify(mockRD).forward(mockRequest, mockResponse);
	}
}
