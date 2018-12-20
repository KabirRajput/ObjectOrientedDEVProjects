package com.fdmgroup.LegendAir.dal;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.entity.Flight;
import com.fdmgroup.LegendAir.entity.FlightTicket;

public class FlightDaoTest {
	@Test
	public void given_Flight_when_addingFlightToDB_then_flightIsPersistedAndResourcesClosed() {
		Flight mockFlight = mock(Flight.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);

		FlightDao fDao = new FlightDao(mockEmf);
		fDao.add(mockFlight);
		InOrder order = inOrder(mockEmf, mockEm, mockEt);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).persist(mockFlight);
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
		when(mockEm.find(Flight.class, id)).thenReturn(null);

		FlightDao fDao = new FlightDao(mockEmf);
		Flight expectedFlight = fDao.getById(id);

		assertNull(expectedFlight);
	}
	
	@Test
	public void given_existingFlightId_then_returnsFlightFromDB() {
		int id = 1000;

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);

		FlightDao fDao = new FlightDao(mockEmf);
		fDao.getById(id);

		InOrder order = inOrder(mockEmf, mockEm, mockEt);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Flight.class, id);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_query_then_ListOfFlight_is_returned_FromDB() {
		String queryString = "SELECT f FROM Flight f";

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<Flight> mockQuery = mock(TypedQuery.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery(queryString, Flight.class)).thenReturn(mockQuery);

		FlightDao fDao = new FlightDao(mockEmf);
		fDao.getByQuery(queryString);

		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQuery);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).createQuery(queryString, Flight.class);
		order.verify(mockQuery).getResultList();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void existing_Flight_is_removed_from_db() {
		//arrange
		int id = 1000;
		
		Flight mockFlight = mock(Flight.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockFlight.getFlightId()).thenReturn(id);
		when(mockEm.find(Flight.class, id)).thenReturn(mockFlight);
		FlightDao fDao = new FlightDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockFlight);
		
		//act
		fDao.remove(mockFlight.getFlightId());
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Flight.class, mockFlight.getFlightId());
		order.verify(mockEm).remove(mockFlight);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void add_flightTicket_to_flight_then_persist_to_db() {
		//arrange
		int id = 66666666;
		
		Flight mockF = mock(Flight.class);
		FlightTicket mockFt = mock(FlightTicket.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockF.getFlightId()).thenReturn(id);
		when(mockEm.find(Flight.class, id)).thenReturn(mockF);
		FlightDao fDao = new FlightDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockF);
		
		//act
		fDao.addFlightTicket(mockF, mockFt);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Flight.class, mockF.getFlightId());
		order.verify(mockF).addFlightTicket(mockFt);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void	get_all_flightTickets_from_flight_method_then_resource_closed() {
		//arrange
		int id = 66666666;
		
		Flight mockFlight = mock(Flight.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockFlight.getFlightId()).thenReturn(id);
		when(mockEm.find(Flight.class, id)).thenReturn(mockFlight);
		FlightDao fDao = new FlightDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockFlight);
		
		//act
		fDao.getFlightTickets(mockFlight);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Flight.class, mockFlight.getFlightId());
		order.verify(mockFlight).getFlightTickets();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void remove_flightTicket_from_flight_then_change_persisted_to_db() {
		//arrange
		int id = 66666666;
		
		Flight mockFlight = mock(Flight.class);
		FlightTicket mockFt = mock(FlightTicket.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockFlight.getFlightId()).thenReturn(id);
		when(mockEm.find(Flight.class, id)).thenReturn(mockFlight);
		FlightDao fDao = new FlightDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockFlight);
		
		//act
		fDao.removeFlightTicket(mockFlight, mockFt);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Flight.class, mockFlight.getFlightId());
		order.verify(mockFlight).removeFlightTicket(mockFt);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void when_given_origin_destination_id_and_date_list_of_departure_arrival_time_and_price_is_returned_from_db() {
		String originIATAId = "PVG";
		String destinationIATAId = "HKG";
		String departureDate = "25-Dec-18";
		String queryString = "SELECT f.flightTicketId, f.departureTime, f.arrivalTime, ft.flightPrice FROM Flight f INNER JOIN f.flightTickets ft WHERE f.laOrigin = '" + originIATAId + "' AND  f.laDestination = '" + destinationIATAId + "' AND ft.flightDate = '" + departureDate + "'";
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery mockQuery = mock(TypedQuery.class);
		List mockList = mock(List.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery(queryString)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockList);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQuery);
		
		FlightDao fd = new FlightDao(mockEmf);
		fd.getFlightTicketsByIdDate(originIATAId, destinationIATAId, departureDate);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).createQuery(queryString);
		order.verify(mockQuery).getResultList();
		order.verify(mockEm).close();
	}
}
