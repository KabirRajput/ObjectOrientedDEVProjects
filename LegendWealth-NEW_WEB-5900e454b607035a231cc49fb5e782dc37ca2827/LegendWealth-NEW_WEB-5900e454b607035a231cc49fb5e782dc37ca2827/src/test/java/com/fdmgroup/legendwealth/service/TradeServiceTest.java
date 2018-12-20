package com.fdmgroup.legendwealth.service;

import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fdmgroup.legendwealth.dal.AssetDao;
import com.fdmgroup.legendwealth.dal.TradeDao;
import com.fdmgroup.legendwealth.entity.Asset;
import com.fdmgroup.legendwealth.entity.Broker;
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.entity.Trade;
import com.fdmgroup.legendwealth.factory.TradeFactory;

public class TradeServiceTest {

	@Test
	public void given_trade_details_create_a_trade_instance() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String transactionTime = dtf.format(now);
		
		double quantity = 0.0;
		double priceTotal = 0.0;
		
		TradeFactory mockTradeFactory = mock(TradeFactory.class);
		TradeDao mockTradeDao = mock(TradeDao.class);
		AssetDao mockAssetDao = mock(AssetDao.class);
		Broker mockBroker = mock(Broker.class);
		Portfolio mockPortfolio = mock(Portfolio.class);
		Asset mockAsset = mock(Asset.class);
		Trade mockTrade = mock(Trade.class);
		
		when(mockTradeFactory.createTrade()).thenReturn(mockTrade);
		
		TradeService tradeService = new TradeService(mockTradeFactory, mockTradeDao, mockAssetDao);
		tradeService.createTradeInstance(mockBroker, mockPortfolio, mockAsset, quantity, priceTotal);
		
		InOrder order = inOrder(mockTrade);
		
		order.verify(mockTrade).setBroker(mockBroker);
		order.verify(mockTrade).setPortfolio(mockPortfolio);
		order.verify(mockTrade).setAsset(mockAsset); 
		order.verify(mockTrade).setTransactionTime(transactionTime);
		order.verify(mockTrade).setQuantity(quantity);
		order.verify(mockTrade).setPriceTotal(priceTotal);
		


	}
	
//	@Test
//	public void add_two_trades_records_for_each_trade() {
//		double quantity = 0.0;
//		double priceTotal = 0.0;
//		
//		TradeFactory mockTradeFactory = mock(TradeFactory.class);
//		TradeDao mockTradeDao = mock(TradeDao.class);
//		AssetDao mockAssetDao = mock(AssetDao.class);
//		Broker mockBroker = mock(Broker.class);
//		Portfolio mockPortfolio = mock(Portfolio.class);
//		Asset mockAsset = mock(Asset.class);
//		Trade mockCashTrade = mock(Trade.class);
//		Trade mockAssetTrade = mock(Trade.class);
//		Asset mockCashAsset = mock(Asset.class);
//		
//		when(mockAssetDao.getById(5)).thenReturn(mockCashAsset);
//		
//	
//		TradeService tradeService = new TradeService(mockTradeFactory, mockTradeDao, mockAssetDao);
//		tradeService.changeAsset(mockBroker, mockPortfolio, mockAsset, quantity, priceTotal);
//		
//		
//		InOrder order = inOrder(mockTradeDao);
//		
//		order.verify(mockTradeDao).add(tradeService.createTradeInstance(mockBroker, mockPortfolio, mockCashAsset, priceTotal, priceTotal));
//		order.verify(mockTradeDao).add(tradeService.createTradeInstance(mockBroker, mockPortfolio, mockAsset, quantity, priceTotal));
//	}
}
