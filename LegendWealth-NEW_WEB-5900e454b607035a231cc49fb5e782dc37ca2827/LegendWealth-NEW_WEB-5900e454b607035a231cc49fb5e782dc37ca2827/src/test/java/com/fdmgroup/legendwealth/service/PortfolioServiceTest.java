package com.fdmgroup.legendwealth.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.fdmgroup.legendwealth.dal.PortfolioDao;
import com.fdmgroup.legendwealth.dal.TradeDao;
import com.fdmgroup.legendwealth.entity.Portfolio;



public class PortfolioServiceTest {
	@Test
	public void when_getPortfolioById_method_is_called_it_invokes_getById_method_of_porftolioDao() {
		
		long id = 1;
		PortfolioDao mockPortfolioDao = mock(PortfolioDao.class);
		TradeDao mockTradeDao = mock(TradeDao.class);
		
		PortfolioService portfolioService = new PortfolioService(mockPortfolioDao, mockTradeDao);
		portfolioService.getPortfolioById(id);
		
		verify(mockPortfolioDao).getById(id);
	}
	
	@Test
	public void when_getPortfolioList_method_is_called_it_invokes_getPortfolioList_method_of_tradeDao() {
		
		PortfolioDao mockPortfolioDao = mock(PortfolioDao.class);
		TradeDao mockTradeDao = mock(TradeDao.class);
		
		PortfolioService portfolioService = new PortfolioService(mockPortfolioDao, mockTradeDao);
		portfolioService.getPortfolioList();
		
		verify(mockTradeDao).getPortfolioList();
	}
	
	@Test
	public void when_getPortfolioAssetSummary_method_is_called_it_invokes_getPortfolioAssetSummary_method_of_tradeDao() {
		
		Portfolio mockPortfolio = mock(Portfolio.class);
		PortfolioDao mockPortfolioDao = mock(PortfolioDao.class);
		TradeDao mockTradeDao = mock(TradeDao.class);
		
		PortfolioService portfolioService = new PortfolioService(mockPortfolioDao, mockTradeDao);
		portfolioService.getPortfolioAssetSummary(mockPortfolio);
		
		verify(mockTradeDao).getPortfolioAssetSummary(mockPortfolio);
	}
}
