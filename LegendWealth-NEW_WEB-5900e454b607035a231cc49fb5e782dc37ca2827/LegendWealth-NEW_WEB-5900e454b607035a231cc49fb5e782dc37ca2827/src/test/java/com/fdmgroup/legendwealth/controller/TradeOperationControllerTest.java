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

import com.fdmgroup.legendwealth.dal.AssetDao;
import com.fdmgroup.legendwealth.entity.Asset;
import com.fdmgroup.legendwealth.entity.Broker;
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.service.PortfolioService;
import com.fdmgroup.legendwealth.service.TradeService;
import com.google.gson.JsonElement;

public class TradeOperationControllerTest {
	@Test
	public void when_trade_operation_is_requested_by_POST_then_forward_to_trade_operation_jsp() throws ServletException, IOException {
		
		double quantity = 0.0;
		double priceTotal = 0.0;
		String message = "";
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);
		ServletContext mockServletContext = mock(ServletContext.class);
		
		Broker mockBroker = mock(Broker.class);
		Portfolio mockPortfolio = mock(Portfolio.class);
		Asset mockAsset = mock(Asset.class);
		
		TradeService mockTradeService = mock(TradeService.class);
		PortfolioService mockPortfolioService = mock(PortfolioService.class);
		
		PortfolioService mockPortfolioSummarySerivce = mock(PortfolioService.class);
		JsonElement mockPortfolioAssetSummary = mock(JsonElement.class);
		
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("active_broker")).thenReturn(mockBroker);
		when(mockSession.getAttribute("portfolio")).thenReturn(mockPortfolio);
		when(mockSession.getAttribute("asset")).thenReturn(mockAsset);
		
		when(mockRequest.getParameter("trade_form_quantityTotal")).thenReturn("0");
		when(mockRequest.getParameter("trade_form_priceTotal")).thenReturn("0");
		
		when(mockSession.getServletContext()).thenReturn(mockServletContext);
		when(mockServletContext.getAttribute("tradeService")).thenReturn(mockTradeService);
		
		when(mockTradeService.changeAsset(mockBroker, mockPortfolio, mockAsset, quantity, priceTotal)).thenReturn(message);
		
		when(mockServletContext.getAttribute("portfolioSummarySerivce")).thenReturn(mockPortfolioSummarySerivce);
		when(mockPortfolioSummarySerivce.getPortfolioAssetSummary(mockPortfolio)).thenReturn(mockPortfolioAssetSummary);
		
		when(mockRequest.getRequestDispatcher("WEB-INF/views/portfolio.jsp")).thenReturn(mockRD);
		
		TradeOperationController tradeOperationController = new TradeOperationController();
		tradeOperationController.doPost(mockRequest, mockResponse);
		
		InOrder order = inOrder(mockRequest, mockSession, mockServletContext, mockPortfolioSummarySerivce, mockTradeService, mockRD);
		
		order.verify(mockRequest).getSession();
		order.verify(mockSession).getAttribute("active_broker");
		order.verify(mockSession).getAttribute("portfolio");
		order.verify(mockSession).getAttribute("asset");
		
		order.verify(mockRequest).getParameter("trade_form_quantityTotal");
		order.verify(mockRequest).getParameter("trade_form_priceTotal");
		
		order.verify(mockSession).getServletContext();
		order.verify(mockServletContext).getAttribute("tradeService");
		
		order.verify(mockTradeService).changeAsset(mockBroker, mockPortfolio, mockAsset, quantity, priceTotal);
		order.verify(mockServletContext).getAttribute("portfolioSummarySerivce");
		order.verify(mockPortfolioSummarySerivce).getPortfolioAssetSummary(mockPortfolio);	
		
		order.verify(mockRequest).setAttribute("portfolioAssetSummary", mockPortfolioAssetSummary);
		order.verify(mockRequest).setAttribute("success_message", message);
		order.verify(mockRequest).getRequestDispatcher("WEB-INF/views/portfolio.jsp");
		order.verify(mockRD).forward(mockRequest, mockResponse);
	}
}
