package com.fdmgroup.legendwealth;

import java.util.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.legendwealth.dal.AssetDao;
import com.fdmgroup.legendwealth.dal.BrokerDao;
import com.fdmgroup.legendwealth.dal.PortfolioDao;
import com.fdmgroup.legendwealth.dal.TradeDao;
import com.fdmgroup.legendwealth.entity.Asset;
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.entity.PortfolioAsset;
import com.fdmgroup.legendwealth.entity.Trade;
import com.fdmgroup.legendwealth.service.DisplayService;

public class RetreiveRunner {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("legend_wealth");
		PortfolioDao portfolioDao = new PortfolioDao(entityManagerFactory);
		BrokerDao brokerDao = new BrokerDao(entityManagerFactory);
		AssetDao assetDao = new AssetDao(entityManagerFactory);
		TradeDao tradeDao = new TradeDao(entityManagerFactory);

		// Set<Portfolio> portfoliosManaging =
		// brokerDao.getById(110).getPortfoliosManaging();
		// System.out.println(brokerDao.getById(110).getLastName() + " " +
		// brokerDao.getById(110).getFirstName());
		// for (Portfolio p : portfoliosManaging) {
		//
		// System.out.println(p);
		// }

//		Set<PortfolioAsset> assetsInPortfolio = portfolioDao.getById(129).getAssetsInPortfolio();
//		int i = 0;
//		for (PortfolioAsset pa : assetsInPortfolio) {
//			System.out.println(i + " " + pa);
//			i++;
//		}
		
//		PortfolioAssetDao portfolioAssetDao = new PortfolioAssetDao(entityManagerFactory);
//		PortfolioAsset portfolioAsset = portfolioAssetDao.getPortfolioAsset(portfolioDao.getById(133), assetDao.getById(13));
//		System.out.println(portfolioAsset);
/*		
		Portfolio p = portfolioDao.getById(129);
		List<Object[]> result = tradeDao.getPortfolioAssetSummary(p);
		for (Object[] row : result) {
			System.out.println((Long) row[0]);
			System.out.println((String) row[1]);
			System.out.println((String) row[2]);
			System.out.println((String) row[3]);
			System.out.println((Double) row[4]);
			System.out.println((Double) row[5]);
		}*/

		

		List<Object[]> result = tradeDao.getCashinPortfolio();
		for (Object[] row : result) {
			System.out.println((Long) row[0]);
			System.out.println((String) row[1]);
			System.out.println((Double) row[2]);
//			System.out.println((Double) row[3]);
//			System.out.println((Double) row[4]);
//			System.out.println((Double) row[5]);
		}		
		
/*		DisplayService<TradeDao, Trade> displayTradeSerivce = (DisplayService<TradeDao, Trade>) session.getServletContext().getAttribute("displayTradeSerivce");
		
		List<Trade> tradeList = displayTradeSerivce.getList();
		System.out.println(tradeList);*/
		
//		Portfolio p = portfolioDao.getById(129);
//		List<Object[]> result = tradeDao.getPortfolioAssetSummary(p);
//		for (Object[] row : result) {
//			System.out.println((Long) row[0] + " | " + (String) row[1] + " | " + (Double) row[2] + " | " + (Double) row[3]);
//		}		
		
//		List<Object[]> result1 = tradeDao.getPortfolioList();
//		for (Object[] row : result1) {
//			System.out.println((String) row[0]);
//			System.out.println((Double) row[1]);
//		}
		

	}
}
