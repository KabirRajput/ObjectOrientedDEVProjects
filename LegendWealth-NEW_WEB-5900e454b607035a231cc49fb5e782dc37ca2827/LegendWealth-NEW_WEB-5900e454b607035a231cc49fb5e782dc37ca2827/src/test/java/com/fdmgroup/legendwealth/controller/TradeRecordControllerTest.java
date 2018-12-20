package com.fdmgroup.legendwealth.controller;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

import com.fdmgroup.legendwealth.dal.TradeDao;
import com.fdmgroup.legendwealth.entity.Trade;
import com.fdmgroup.legendwealth.service.DisplayService;
import com.fdmgroup.legendwealth.service.PortfolioService;


public class TradeRecordControllerTest {
	@Test
	public void when_trade_record_is_requested_by_GET_then_forward_to_trade_record_jsp() throws ServletException, IOException {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);
		ServletContext mockServletContext = mock(ServletContext.class);
		TradeDao mockTradeDao = mock(TradeDao.class);
		List<Trade> mockTradeList = mock(List.class);
		

		
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockServletContext);
		when(mockServletContext.getAttribute("tradeDao")).thenReturn(mockTradeDao);
		when(mockTradeDao.getList()).thenReturn(mockTradeList);
		when(mockRequest.getRequestDispatcher("WEB-INF/views/trade-record.jsp")).thenReturn(mockRD);
		
		TradeRecordController mockTradeRecordController = new TradeRecordController();
		mockTradeRecordController.doGet(mockRequest, mockResponse);
		
		InOrder order = inOrder(mockRequest, mockRD, mockSession);
		order.verify(mockSession).setAttribute("tradeList", mockTradeList);
		order.verify(mockRequest).getRequestDispatcher("WEB-INF/views/trade-record.jsp");
		order.verify(mockRD).forward(mockRequest, mockResponse);
	}
}
