package com.fdmgroup.legendwealth.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fdmgroup.legendwealth.dal.AssetDao;
import com.fdmgroup.legendwealth.dal.AssetTypeDao;
import com.fdmgroup.legendwealth.dal.BrokerDao;
import com.fdmgroup.legendwealth.dal.PortfolioDao;
import com.fdmgroup.legendwealth.dal.TradeDao;
import com.fdmgroup.legendwealth.factory.TradeFactory;
import com.fdmgroup.legendwealth.service.PortfolioService;
import com.fdmgroup.legendwealth.service.TradeService;

public class ApplicationContextListener implements ServletContextListener {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("legend_wealth");


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Servlet Context Initialized");
		
		AssetDao assetDao = new AssetDao(entityManagerFactory);
		AssetTypeDao assetTypeDao = new AssetTypeDao(entityManagerFactory);
		BrokerDao brokerDao = new BrokerDao(entityManagerFactory);
		PortfolioDao portfolioDao = new PortfolioDao(entityManagerFactory);
		TradeDao tradeDao = new TradeDao(entityManagerFactory);
		
		TradeFactory tradeFactory = new TradeFactory();
		
		
		
		
		ServletContext servletContext = sce.getServletContext();
		
		servletContext.setAttribute("assetDao",assetDao);
		servletContext.setAttribute("assetTypeDao",assetTypeDao);
		servletContext.setAttribute("brokerDao",brokerDao);
		servletContext.setAttribute("portfolioDao",portfolioDao);
		servletContext.setAttribute("tradeDao",tradeDao);
		
//		LoginService loginService = new LoginService(brokerDao);
//		DisplayService displayTradeSerivce = new DisplayService(tradeDao);
//		DateTimeService dateTimeService = new DateTimeService(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		TradeService tradeService = new TradeService(tradeFactory, tradeDao, assetDao);
		PortfolioService portfolioSummarySerivce = new PortfolioService(portfolioDao, tradeDao);
		
//		servletContext.setAttribute("loginService",loginService);
//		servletContext.setAttribute("displayTradeSerivce",displayTradeSerivce);
//		servletContext.setAttribute("dateTimeService",dateTimeService);
		servletContext.setAttribute("tradeService",tradeService);
		servletContext.setAttribute("portfolioSummarySerivce",portfolioSummarySerivce);
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Servlet Context Destroyed");
	}
	

}
