package com.fdmgroup.legendwealth.dal;

import com.fdmgroup.legendwealth.entity.Broker;

import javax.persistence.*;

import org.junit.*;
import org.mockito.InOrder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BrokerDaoTest {

	@Test
	public void given_existingBrokerUsername_and_Password_when_findingByUsername_and_Password_then_returnsBrokerFromDataBase() {
		String username = "harry.porter";
		String password = "harry.porter123";
		
		Broker mockBroker = mock(Broker.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<Broker> mockQ = mock(TypedQuery.class);

		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT b FROM Broker b WHERE b.username = :username AND b.password = :password", Broker.class)).thenReturn(mockQ);
		when(mockQ.setParameter("username", username)).thenReturn(mockQ);
		when(mockQ.setParameter("password", password)).thenReturn(mockQ);
		when(mockQ.getSingleResult()).thenReturn(mockBroker);

		BrokerDao brokerDao = new BrokerDao(mockEmf);
		brokerDao.getBrokerByUsernameAndPassword(username, password);

		InOrder order = inOrder(mockEmf, mockEm, mockQ, mockEt);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEm).createQuery("SELECT b FROM Broker b WHERE b.username = :username AND b.password = :password", Broker.class);
		order.verify(mockQ).setParameter("username", username);
		order.verify(mockQ).setParameter("password", password);
		order.verify(mockEt).begin();
		order.verify(mockQ).getSingleResult();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_not_existingBrokerUsername_and_Password_when_findingByUsername_and_Password_then_returnsNull() {
		String username = "alan.walker";
		String password = "alan.walker123";
		

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<Broker> mockQ = mock(TypedQuery.class);

		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT b FROM Broker b WHERE b.username = :username AND b.password = :password", Broker.class)).thenReturn(mockQ);
		when(mockQ.setParameter("username", username)).thenReturn(mockQ);
		when(mockQ.setParameter("password", password)).thenReturn(mockQ);


		BrokerDao brokerDao = new BrokerDao(mockEmf);
		
		assertNull(brokerDao.getBrokerByUsernameAndPassword(username, password));
		

		
	}
	
}
