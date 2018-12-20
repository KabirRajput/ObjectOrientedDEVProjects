package com.fdmgroup.legendwealth.service;

import java.util.ArrayList;
import java.util.List;
import com.fdmgroup.legendwealth.dal.PortfolioDao;
import com.fdmgroup.legendwealth.dal.TradeDao;
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.entity.PortfolioAsset;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class PortfolioService {
	private TradeDao tradeDao;
	private PortfolioDao portfolioDao;

	public PortfolioService(PortfolioDao portfolioDao, TradeDao tradeDao) {
		this.portfolioDao = portfolioDao;
		this.tradeDao = tradeDao;
	}
	
	public Portfolio getPortfolioById(Long id) {
		return portfolioDao.getById(id);
	}
	
	public List<Object[]> getPortfolioList() {
		return tradeDao.getPortfolioList();
	}
	
	public JsonElement getPortfolioAssetSummary(Portfolio p) {
		List<Object[]> result = tradeDao.getPortfolioAssetSummary(p);
		Gson gson = new Gson();
		List<PortfolioAsset> portfolioAssetSummaryList = new ArrayList<>();
		for (Object[] pa : result) {
			portfolioAssetSummaryList.add(new PortfolioAsset(Long.parseLong(pa[0].toString()), pa[1].toString(), Long.parseLong(pa[2].toString()), pa[3].toString(), pa[4].toString(), Double.parseDouble(pa[5].toString()), Double.parseDouble(pa[6].toString()), Double.parseDouble(pa[7].toString())));
		}
		JsonElement portfolioAssetSummary = gson.toJsonTree(portfolioAssetSummaryList);
		return portfolioAssetSummary;
	}

}
