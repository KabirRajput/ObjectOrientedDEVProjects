package com.fdmgroup.legendwealth.dal;

import org.junit.*;
import org.mockito.InOrder;

import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.entity.Trade;

import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;



public class PortfolioDaoTest {
	
	@Test
	public void add_relationship_bewteen_portfolio_and_broker_and_asset() {
		Portfolio mockPortfolio = mock(Portfolio.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		PortfolioDao portfolioDao = new PortfolioDao(mockEmf);
		portfolioDao.addRelationship(mockPortfolio);

		InOrder order = inOrder(mockEmf, mockEm, mockEt);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).merge(mockPortfolio);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
}
