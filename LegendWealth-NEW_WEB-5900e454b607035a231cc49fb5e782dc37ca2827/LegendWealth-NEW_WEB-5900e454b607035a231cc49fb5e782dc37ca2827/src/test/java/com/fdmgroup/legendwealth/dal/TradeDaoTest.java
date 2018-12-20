package com.fdmgroup.legendwealth.dal;
import com.fdmgroup.legendwealth.entity.Broker;
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.entity.Trade;

import javax.persistence.*;

import org.junit.*;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

import java.util.List;



public class TradeDaoTest {

	@Test
	public void given_Trade_when_addingTradeToDataBase_then_TradeIsPersistedAndResourcesClosed() {
		Trade mockTrade = mock(Trade.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		TradeDao tradeDao = new TradeDao(mockEmf);
		tradeDao.add(mockTrade);
		
		InOrder order = inOrder(mockEmf, mockEm, mockEt);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).persist(mockTrade);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	
	@Test
	public void when_getPortfolioList_it_invokes_getResultList_of_query() {
		List<Object[]> mockList = mock(List.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<Object[]> mockQ = mock(TypedQuery.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT p.id, p.name, SUM(t.priceTotal) FROM Trade t JOIN t.portfolio p GROUP BY p.id, p.name ORDER BY p.id", Object[].class)).thenReturn(mockQ);
		when(mockQ.getResultList()).thenReturn(mockList);
		
		TradeDao tradeDao = new TradeDao(mockEmf);
		tradeDao.getPortfolioList();
		
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQ);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEm).createQuery("SELECT p.id, p.name, SUM(t.priceTotal) FROM Trade t JOIN t.portfolio p GROUP BY p.id, p.name ORDER BY p.id", Object[].class);
		order.verify(mockEt).begin();
		order.verify(mockQ).getResultList();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void when_getPortfolioAssetSummary_it_invokes_getResultList_of_query() {
		List<Object[]> mockList = mock(List.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<Object[]> mockQ = mock(TypedQuery.class);
		Portfolio mockPortfolio = mock(Portfolio.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT t.portfolio.id, t.portfolio.name, a.assetId, a.code, a.assetType.description, a.price, SUM(t.quantity), SUM(t.quantity*a.price) FROM Trade t JOIN t.asset a GROUP BY t.portfolio.id, t.portfolio.name, a.assetId, a.code, a.assetType.description, a.price HAVING t.portfolio = :portfolio AND SUM(t.quantity) > 0 ORDER BY SUM(t.quantity*a.price) DESC", Object[].class)).thenReturn(mockQ);
		when(mockQ.getResultList()).thenReturn(mockList);
		
		TradeDao tradeDao = new TradeDao(mockEmf);
		tradeDao.getPortfolioAssetSummary(mockPortfolio);
		
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQ);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEm).createQuery("SELECT t.portfolio.id, t.portfolio.name, a.assetId, a.code, a.assetType.description, a.price, SUM(t.quantity), SUM(t.quantity*a.price) FROM Trade t JOIN t.asset a GROUP BY t.portfolio.id, t.portfolio.name, a.assetId, a.code, a.assetType.description, a.price HAVING t.portfolio = :portfolio AND SUM(t.quantity) > 0 ORDER BY SUM(t.quantity*a.price) DESC", Object[].class);
		order.verify(mockQ).setParameter("portfolio", mockPortfolio);
		order.verify(mockEt).begin();
		order.verify(mockQ).getResultList();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void when_getCashinPortfolio_it_invokes_getResultList_of_query() {
		List<Object[]> mockList = mock(List.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<Object[]> mockQ = mock(TypedQuery.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT t.portfolio.id, a.assetType.description, SUM(t.quantity*a.price) FROM Trade t JOIN t.asset a GROUP BY t.portfolio.id, a.assetType.description HAVING a.assetType.description = 'Cash' ORDER BY t.portfolio.id", Object[].class)).thenReturn(mockQ);
		when(mockQ.getResultList()).thenReturn(mockList);
		
		TradeDao tradeDao = new TradeDao(mockEmf);
		tradeDao.getCashinPortfolio();
		
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQ);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEm).createQuery("SELECT t.portfolio.id, a.assetType.description, SUM(t.quantity*a.price) FROM Trade t JOIN t.asset a GROUP BY t.portfolio.id, a.assetType.description HAVING a.assetType.description = 'Cash' ORDER BY t.portfolio.id", Object[].class);
		order.verify(mockEt).begin();
		order.verify(mockQ).getResultList();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
}
