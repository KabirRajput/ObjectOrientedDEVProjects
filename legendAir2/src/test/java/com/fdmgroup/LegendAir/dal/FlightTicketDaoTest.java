package com.fdmgroup.LegendAir.dal;
import javax.persistence.*;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.entity.FlightTicket;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FlightTicketDaoTest {

	@Test
	public void flightTicket_is_persisted_to_db_when_adding() {
		FlightTicket mockFT = mock(FlightTicket.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		InOrder order = inOrder(mockEmf, mockEm, mockEt);
		
		FlightTicketDao ftDao = new FlightTicketDao(mockEmf);
		ftDao.add(mockFT);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).persist(mockFT);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void DB_returns_null_when_id_does_not_exist() {
		int id = 1000;
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		InOrder order = inOrder(mockEmf, mockEm, mockEt);
		
		FlightTicketDao ftDao = new FlightTicketDao(mockEmf);
		FlightTicket expectedFt = ftDao.getById(id);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(FlightTicket.class, id);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		assertNull(expectedFt);
	}
	
	@Test
	public void DB_returns_flight_ticket_when_it_exists() {
		int id = 1000;
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		InOrder order = inOrder(mockEmf, mockEm, mockEt);
		
		FlightTicketDao ftDao = new FlightTicketDao(mockEmf);
		ftDao.getById(id);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(FlightTicket.class, id);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_query_then_list_of_flight_tickets_is_returned() {
		String queryString = "SELECT ft FROM FlightTicket ft WHERE ft.class = 'Economy'";
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		@SuppressWarnings("unchecked")
		TypedQuery<FlightTicket> mockQuery = mock(TypedQuery.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery(queryString, FlightTicket.class)).thenReturn(mockQuery);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQuery);
		
		FlightTicketDao ftDao = new FlightTicketDao(mockEmf);
		ftDao.getByQuery(queryString);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).createQuery(queryString, FlightTicket.class);
		order.verify(mockQuery).getResultList();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void existing_flight_ticket_is_removed_from_db() {
		int id = 1000;
		FlightTicket mockFT = mock(FlightTicket.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockFT.getFlightTicketId()).thenReturn(id);
		when(mockEm.find(FlightTicket.class, id)).thenReturn(mockFT);
		InOrder order = inOrder(mockEmf, mockEm, mockEt);
		
		FlightTicketDao ftDao = new FlightTicketDao(mockEmf);
		ftDao.remove(id);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(FlightTicket.class, mockFT.getFlightTicketId());
		order.verify(mockEm).remove(mockFT);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
}
