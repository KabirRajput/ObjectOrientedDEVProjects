package com.fdmgroup.LegendAir.dal;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import javax.persistence.*;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.entity.Passenger;
import com.fdmgroup.LegendAir.entity.FlightTicket;

public class PassengerDaoTest {
	@Test
	public void given_Passenger_when_addingPassengerToDB_then_passengerIsPersistedAndResourcesClosed() {
		Passenger mockPassenger = mock(Passenger.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);

		PassengerDao pDao = new PassengerDao(mockEmf);
		pDao.add(mockPassenger);
		InOrder order = inOrder(mockEmf, mockEm, mockEt);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).persist(mockPassenger);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void DB_returnsNull_when_idDoesNotExist() {
		int id = 1000;

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Passenger.class, id)).thenReturn(null);

		PassengerDao pDao = new PassengerDao(mockEmf);
		Passenger expectedPassenger = pDao.getById(id);

		assertNull(expectedPassenger);
	}
	
	@Test
	public void given_existingPassengerId_then_returnsPassengerFromDB() {
		int id = 1000;

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);

		PassengerDao pDao = new PassengerDao(mockEmf);
		pDao.getById(id);

		InOrder order = inOrder(mockEmf, mockEm, mockEt);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Passenger.class, id);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_query_then_ListOfPassenger_is_returned_FromDB() {
		String queryString = "SELECT p FROM Passenger p WHERE b.firstName = 'andrew'";

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<Passenger> mockQuery = mock(TypedQuery.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery(queryString, Passenger.class)).thenReturn(mockQuery);

		PassengerDao pDao = new PassengerDao(mockEmf);
		pDao.getByQuery(queryString);

		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQuery);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).createQuery(queryString, Passenger.class);
		order.verify(mockQuery).getResultList();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void existing_Passenger_is_removed_from_db() {
		//arrange
		int id = 1000;
		
		Passenger mockPassenger = mock(Passenger.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockPassenger.getPassengerId()).thenReturn(id);
		when(mockEm.find(Passenger.class, id)).thenReturn(mockPassenger);
		PassengerDao pDao = new PassengerDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockPassenger);
		
		//act
		pDao.remove(mockPassenger.getPassengerId());
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Passenger.class, mockPassenger.getPassengerId());
		order.verify(mockEm).remove(mockPassenger);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void flight_ticket_is_added_to_passenger_then_persisted_to_db() {
		//arrange
		int id = 6;
		
		Passenger mockPass = mock(Passenger.class);
		FlightTicket mockFt = mock(FlightTicket.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockPass.getPassengerId()).thenReturn(id);
		when(mockEm.find(Passenger.class, id)).thenReturn(mockPass);
		PassengerDao pDao = new PassengerDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockPass);
		
		//act
		pDao.addFlightTicket(mockPass, mockFt);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Passenger.class, mockPass.getPassengerId());
		order.verify(mockPass).addFlightTicket(mockFt);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void	get_all_flightTickets_from_booking_method_then_resource_closed() {
		//arrange
		int id = 6;
		
		Passenger mockPass = mock(Passenger.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockPass.getPassengerId()).thenReturn(id);
		when(mockEm.find(Passenger.class, id)).thenReturn(mockPass);
		PassengerDao pDao = new PassengerDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockPass);
		
		//act
		pDao.getFlightTickets(mockPass);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Passenger.class, mockPass.getPassengerId());
		order.verify(mockPass).getFlightTickets();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void remove_booking_from_db_then_change_persisted_to_db() {
		//arrange
		int id = 6;
		
		Passenger mockPassenger = mock(Passenger.class);
		FlightTicket mockFt = mock(FlightTicket.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockPassenger.getPassengerId()).thenReturn(id);
		when(mockEm.find(Passenger.class, id)).thenReturn(mockPassenger);
		PassengerDao pDao = new PassengerDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockPassenger);
		
		//act
		pDao.removeFlightTicket(mockPassenger, mockFt);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Passenger.class, mockPassenger.getPassengerId());
		order.verify(mockPassenger).removeFlightTicket(mockFt);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
}
