package com.fdmgroup.legendwealth.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import com.fdmgroup.legendwealth.dal.AssetDao;
import com.fdmgroup.legendwealth.dal.TradeDao;
import com.fdmgroup.legendwealth.entity.Asset;
import com.fdmgroup.legendwealth.entity.Broker;
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.entity.Trade;
import com.fdmgroup.legendwealth.factory.TradeFactory;

public class TradeService {
	private TradeFactory tradeFactory;
	private TradeDao tradeDao;
	private AssetDao assetDao;
	
	public TradeService(TradeFactory tradeFactory, TradeDao tradeDao, AssetDao assetDao) {
		super();
		this.tradeFactory = tradeFactory;
		this.tradeDao = tradeDao;
		this.assetDao = assetDao;
	}
	
	public String changeAsset(Broker broker, Portfolio portfolio, Asset asset, double quantity,
			double priceTotal) {

		// Cash Operation
		tradeDao.add(createTradeInstance(broker, portfolio, assetDao.getById(5), priceTotal, priceTotal));
		
		// Asset Operation
		tradeDao.add(createTradeInstance(broker, portfolio, asset, quantity, priceTotal * -1));
		
		if (priceTotal > 0) {
			return "Converted " + Math.abs(quantity) + " " + asset.getCode() + " to " + Math.abs(priceTotal) + " USD successfully.";		
		} else {
			return "Purchased " + Math.abs(quantity) + " " + asset.getCode() + " by " + Math.abs(priceTotal) + " USD successfully.";			
		}
	}

	public Trade createTradeInstance(Broker broker, Portfolio portfolio, Asset asset, double quantity, double priceTotal) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String transactionTime = dtf.format(now);
		
		Trade trade = tradeFactory.createTrade();
		trade.setBroker(broker);
		trade.setPortfolio(portfolio);
		trade.setAsset(asset); 
		trade.setTransactionTime(transactionTime);
		trade.setQuantity(quantity);
		trade.setPriceTotal(priceTotal);
		return trade;
	}

}
