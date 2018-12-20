package com.fdmgroup.legendwealth.dal;

import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.mockito.*;

import com.fdmgroup.legendwealth.entity.Trade;


public class GenericDaoTest {
	
	@Test
	public void given_Instance_when_addingInstanceToDataBase_then_InstanceIsPersistedAndResourcesClosed() {
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
	public void given_existingId_when_findingById_then_returnsFromDataBase() {
		long id = 1;
		
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		
		TradeDao tradeDao = new TradeDao(mockEmf);
		tradeDao.getById(id);
		
		InOrder order = inOrder(mockEmf, mockEm);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Trade.class, id);
		order.verify(mockEm).close();
	}
	
	@Test
	public void query_list_then_returnsFromDataBase() {
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<Trade> mockQ = mock(TypedQuery.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT e FROM Trade e")).thenReturn(mockQ);
		
		TradeDao tradeDao = new TradeDao(mockEmf);
		tradeDao.getList();
		
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQ);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockQ).getResultList();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_Instance_when_mergingInstanceToDataBase_then_InstanceIsMergedAndResourcesClosed() {
		Trade mockTrade = mock(Trade.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		TradeDao tradeDao = new TradeDao(mockEmf);
		tradeDao.merge(mockTrade);

		InOrder order = inOrder(mockEmf, mockEm, mockEt);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).merge(mockTrade);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
}
