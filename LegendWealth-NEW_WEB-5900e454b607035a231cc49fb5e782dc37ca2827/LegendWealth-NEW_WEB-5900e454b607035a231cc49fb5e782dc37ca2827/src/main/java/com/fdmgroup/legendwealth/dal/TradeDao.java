package com.fdmgroup.legendwealth.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.fdmgroup.legendwealth.entity.Asset;
import com.fdmgroup.legendwealth.entity.Broker;
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.entity.PortfolioAsset;
import com.fdmgroup.legendwealth.entity.Trade;

public class TradeDao extends GenericDao<Trade> {
	private EntityManagerFactory entityManagerFactory;

	public TradeDao(EntityManagerFactory entityManagerFactory) {
		super(Trade.class, entityManagerFactory);
		this.entityManagerFactory = entityManagerFactory;
	}

	public void add(Trade trade) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(trade);
		entityTransaction.commit();
		entityManager.close();
		
//		// Update Entity Relationships
//		PortfolioDao portfolioDao = new PortfolioDao(entityManagerFactory);
//		BrokerDao brokerDao = new BrokerDao(entityManagerFactory);
//		AssetDao assetDao = new AssetDao(entityManagerFactory);
//		PortfolioAssetDao portfolioAssetDao = new PortfolioAssetDao(entityManagerFactory);
//		
//		// Update Broker_portfolio table
//		System.out.println(" ");
//		System.out.println(" ");
//		System.out.println(" ");
//		System.out.println("!!!!!!!! Update new table");
//		Portfolio p = portfolioDao.getById(trade.getPortfolio().getPortfolioId());
//		System.out.println("portfolioDao.getById(trade.getPortfolio().getPortfolioId()): " + p);
//		Broker b = brokerDao.getById(trade.getBroker().getBrokerId());
//		System.out.println("brokerDao.getById(trade.getBroker().getBrokerId()): " + b);
//		p.addBroker(b);
//		System.out.println("Before Merge Portfolio :" + p);
//		System.out.println("Before Merge Broker :" + b);
//		portfolioDao.merge(p);
//		System.out.println("Merge: " + p);
//		
//		// Update Portfolio_asset table
//		System.out.println("trade.getAsset().getAssetId(): " + trade.getAsset().getAssetId());
//		Asset a = assetDao.getById(trade.getAsset().getAssetId());
//		System.out.println("Asset by TradeDao: " + a);
//		PortfolioAssetId paId = new PortfolioAssetId(p, a);
//		System.out.println("paId: " + paId);
//		PortfolioAsset existingPortfolioAsset = portfolioAssetDao.getPortfolioAsset(p, a,
//				portfolioDao.getById(p.getPortfolioId()), assetDao.getById(a.getAssetId()));
//		
//		double quantity = 0;
//		if (existingPortfolioAsset != null) {
//			quantity = existingPortfolioAsset.getQuantity() + trade.getQuantity();
//		} else {
//			quantity = trade.getQuantity();
//		}
//		PortfolioAsset pa = new PortfolioAsset(paId, quantity);
//		portfolioAssetDao.merge(pa);
	}

	
	public List<Object[]> getPortfolioList() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		List<Object[]> result = null;
		Query query = entityManager.createQuery("SELECT p.id, p.name, SUM(t.priceTotal) FROM Trade t JOIN t.portfolio p GROUP BY p.id, p.name ORDER BY p.id", Object[].class);
		entityTransaction.begin();
		try {
			result = query.getResultList();
		}
		catch (NoResultException nre){
			//Ignore this because as per your logic this is ok, but we should log it
		}
		finally {			
			entityTransaction.commit();
			entityManager.close();
		}
		
		return result;
	}	
	
	public List<Object[]> getPortfolioAssetSummary(Portfolio p) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		List<Object[]> result = null;
		Query query = entityManager.createQuery("SELECT t.portfolio.id, t.portfolio.name, a.assetId, a.code, a.assetType.description, a.price, SUM(t.quantity), SUM(t.quantity*a.price) FROM Trade t JOIN t.asset a GROUP BY t.portfolio.id, t.portfolio.name, a.assetId, a.code, a.assetType.description, a.price HAVING t.portfolio = :portfolio AND SUM(t.quantity) > 0 ORDER BY SUM(t.quantity*a.price) DESC", Object[].class);

		query.setParameter("portfolio", p);
		entityTransaction.begin();
		try {
			result = query.getResultList();
		}
		catch (NoResultException nre){
			//Ignore this because as per your logic this is ok, but we should log it
		}
		finally {			
			entityTransaction.commit();
			entityManager.close();
		}
		return result;
	}

	public List<Object[]> getCashinPortfolio() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		List<Object[]> result = null;
		Query query = entityManager.createQuery("SELECT t.portfolio.id, a.assetType.description, SUM(t.quantity*a.price) FROM Trade t JOIN t.asset a GROUP BY t.portfolio.id, a.assetType.description HAVING a.assetType.description = 'Cash' ORDER BY t.portfolio.id", Object[].class);
		entityTransaction.begin();
		try {
			result = query.getResultList();
		}
		catch (NoResultException nre){
			//Ignore this because as per your logic this is ok, but we should log it
		}
		finally {			
			entityTransaction.commit();
			entityManager.close();
		}
		return result;
	}
}
