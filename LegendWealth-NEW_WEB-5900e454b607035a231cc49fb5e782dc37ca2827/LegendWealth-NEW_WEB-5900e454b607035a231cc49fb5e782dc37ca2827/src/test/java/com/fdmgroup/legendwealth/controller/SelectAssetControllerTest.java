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
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.service.PortfolioService;

public class SelectAssetControllerTest {
	@Test
	public void when_select_asset_is_requested_by_GET_then_forward_to_select_asset_jsp() throws ServletException, IOException {
		long longId = Long.parseLong("1");
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);
		PortfolioService mockPortfolioSummarySerivce = mock(PortfolioService.class);
		ServletContext mockServletContext = mock(ServletContext.class);
		
		Portfolio mockPorfolio = mock(Portfolio.class);
		AssetDao mockAssetDao = mock(AssetDao.class);
		List<Asset> mockAssetList = mock(List.class);
		
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockRequest.getParameter("id")).thenReturn("1");
		when(mockSession.getServletContext()).thenReturn(mockServletContext);
		when(mockServletContext.getAttribute("portfolioSummarySerivce")).thenReturn(mockPortfolioSummarySerivce);
		when(mockPortfolioSummarySerivce.getPortfolioById(longId)).thenReturn(mockPorfolio);
		when(mockServletContext.getAttribute("assetDao")).thenReturn(mockAssetDao);
		when(mockAssetDao.getList()).thenReturn(mockAssetList);
		when(mockRequest.getRequestDispatcher("WEB-INF/views/select-asset.jsp")).thenReturn(mockRD);
		
		SelectAssetController selectAssetController = new SelectAssetController();
		selectAssetController.doGet(mockRequest, mockResponse);
		
		InOrder order = inOrder(mockRequest, mockSession, mockAssetDao, mockServletContext, mockPortfolioSummarySerivce, mockRD);
		
		order.verify(mockRequest).getSession();
		order.verify(mockRequest).getParameter("id");
		order.verify(mockSession).getServletContext();
		order.verify(mockServletContext).getAttribute("portfolioSummarySerivce");
		order.verify(mockPortfolioSummarySerivce).getPortfolioById(longId);
		order.verify(mockServletContext).getAttribute("assetDao");
		order.verify(mockAssetDao).getList();
		order.verify(mockRequest).setAttribute("assetList", mockAssetList);
		order.verify(mockRequest).getRequestDispatcher("WEB-INF/views/select-asset.jsp");
		order.verify(mockRD).forward(mockRequest, mockResponse);
	}
}
