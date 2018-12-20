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

import com.fdmgroup.legendwealth.dal.AssetDao;
import com.fdmgroup.legendwealth.dal.PortfolioDao;
import com.fdmgroup.legendwealth.entity.Asset;
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.service.PortfolioService;
import com.google.gson.JsonElement;

public class TradeDetailControllerTest {
	@Test
	public void when_trade_detail_is_requested_by_GET_then_forward_to_trade_detail_jsp() throws ServletException, IOException {
		long longId = 1;
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);
		PortfolioService mockPortfolioSummarySerivce = mock(PortfolioService.class);
		ServletContext mockServletContext = mock(ServletContext.class);
		
		JsonElement mockPortfolioAssetSummary = mock(JsonElement.class);	
		PortfolioDao mockPortfolioDao = mock(PortfolioDao.class);
		Asset mockAsset = mock(Asset.class);
		Portfolio mockPortfolio = mock(Portfolio.class);
		AssetDao mockAssetDao = mock(AssetDao.class);
		
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockServletContext);
		when(mockServletContext.getAttribute("assetDao")).thenReturn(mockAssetDao);
		when(mockRequest.getParameter("asset_id")).thenReturn("1");
		when(mockAssetDao.getById(longId)).thenReturn(mockAsset);
		
		when(mockServletContext.getAttribute("portfolioDao")).thenReturn(mockPortfolioDao);
		when(mockRequest.getParameter("portfolio_id")).thenReturn("1");
		when(mockPortfolioDao.getById(longId)).thenReturn(mockPortfolio);
		
		when(mockServletContext.getAttribute("portfolioSummarySerivce")).thenReturn(mockPortfolioSummarySerivce);
		when(mockPortfolioSummarySerivce.getPortfolioAssetSummary(mockPortfolio)).thenReturn(mockPortfolioAssetSummary);
		
		when(mockRequest.getRequestDispatcher("WEB-INF/views/trade-detail.jsp")).thenReturn(mockRD);
		
		TradeDetailController tradeDetailController = new TradeDetailController();
		tradeDetailController.doGet(mockRequest, mockResponse);
		
		InOrder order = inOrder(mockRequest, mockServletContext, mockSession, mockAssetDao, mockPortfolioDao, mockPortfolioSummarySerivce, mockRD);
		
		order.verify(mockRequest).getSession();

		order.verify(mockServletContext).getAttribute("assetDao");
		order.verify(mockRequest).getParameter("asset_id");
		order.verify(mockAssetDao).getById(longId);		
		
		order.verify(mockServletContext).getAttribute("portfolioDao");
		order.verify(mockRequest).getParameter("portfolio_id");
		order.verify(mockPortfolioDao).getById(longId);
		
		order.verify(mockServletContext).getAttribute("portfolioSummarySerivce");
		order.verify(mockPortfolioSummarySerivce).getPortfolioAssetSummary(mockPortfolio);
		
		order.verify(mockRequest).setAttribute("portfolioAssetSummary", mockPortfolioAssetSummary);
		order.verify(mockSession).setAttribute("asset", mockAsset);
		order.verify(mockRequest).getRequestDispatcher("WEB-INF/views/trade-detail.jsp");
		order.verify(mockRD).forward(mockRequest, mockResponse);
	}
}
