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

import com.fdmgroup.LegendAir.entity.Airport;
import com.fdmgroup.LegendAir.entity.Flight;

public class AirportDaoTest {	
	@Test
	public void add_flight_to_airport_flightOrigin_then_persist_to_db() {
		//arrange
		String iataId = "PVG";
		
		Airport mockA = mock(Airport.class);
		Flight mockF = mock(Flight.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockA.getAirportIATAId()).thenReturn(iataId);
		when(mockEm.find(Airport.class, iataId)).thenReturn(mockA);
		AirportDao aDao = new AirportDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockA);
		
		//act
		aDao.addFlightToOrigin(mockA, mockF);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Airport.class, mockA.getAirportIATAId());
		order.verify(mockA).addFlightToOrigin(mockF);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void add_flight_to_airport_flightDestination_then_persist_to_db() {
		//arrange
		String iataId = "MXP";
		int flightId = 1;
		
		Airport mockA = mock(Airport.class);
		Flight mockF = mock(Flight.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockF.getFlightId()).thenReturn(flightId);
		when(mockEm.find(Flight.class, flightId)).thenReturn(mockF);
		when(mockA.getAirportIATAId()).thenReturn(iataId);
		when(mockEm.find(Airport.class, iataId)).thenReturn(mockA);
		AirportDao aDao = new AirportDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockA);
		
		//act
		aDao.addFlightToDestination(mockA, mockF);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Flight.class, mockF.getFlightId());
		order.verify(mockEm).find(Airport.class, mockA.getAirportIATAId());
		order.verify(mockA).addFlightToDestination(mockF);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void	get_all_flightOrigins_from_airport_then_resource_closed() {
		//arrange
		String iataId = "MXP";
		
		Airport mockA = mock(Airport.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockA.getAirportIATAId()).thenReturn(iataId);
		when(mockEm.find(Airport.class, iataId)).thenReturn(mockA);
		AirportDao aDao = new AirportDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockA);
		
		//act
		aDao.getFlightOrigins(mockA);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Airport.class, mockA.getAirportIATAId());
		order.verify(mockA).getFlightOrigins();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void	get_all_flightDestinations_from_airport_then_resource_closed() {
		//arrange
		String iataId = "MXP";
		
		Airport mockA = mock(Airport.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockA.getAirportIATAId()).thenReturn(iataId);
		when(mockEm.find(Airport.class, iataId)).thenReturn(mockA);
		AirportDao aDao = new AirportDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockA);
		
		//act
		aDao.getFlightDestinations(mockA);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Airport.class, mockA.getAirportIATAId());
		order.verify(mockA).getFlightDestinations();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void remove_flight_from_flightOrigins_then_change_persisted_to_db() {
		//arrange
		String iataId = "MXP";
		
		Airport mockA = mock(Airport.class);
		Flight mockF = mock(Flight.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockA.getAirportIATAId()).thenReturn(iataId);
		when(mockEm.find(Airport.class, iataId)).thenReturn(mockA);
		AirportDao aDao = new AirportDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockA);
		
		//act
		aDao.removeFlightFromOrigin(mockA, mockF);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Airport.class, mockA.getAirportIATAId());
		order.verify(mockA).removeFlightFromOrigin(mockF);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void remove_flight_from_flightDestinations_then_change_persisted_to_db() {
		//arrange
		String iataId = "MXP";
		
		Airport mockA = mock(Airport.class);
		Flight mockF = mock(Flight.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockA.getAirportIATAId()).thenReturn(iataId);
		when(mockEm.find(Airport.class, iataId)).thenReturn(mockA);
		AirportDao aDao = new AirportDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockA);
		
		//act
		aDao.removeFlightFromDestination(mockA, mockF);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Airport.class, mockA.getAirportIATAId());
		order.verify(mockA).removeFlightFromDestination(mockF);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void when_searching_by_list_of_city_name_city_IATAId_is_returned() {
		String city = "Shanghai";
		String queryString = "SELECT a.name, a.city, a.airportIATAId FROM Airport a WHERE a.city LIKE '" + city + "%'";
		TypedQuery mockQuery = mock(TypedQuery.class);
		List mockList = mock(List.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery(queryString)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockList);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQuery);
		
		AirportDao ad = new AirportDao(mockEmf);
		ad.getByCity(city);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).createQuery(queryString);
		order.verify(mockQuery).getResultList();
		order.verify(mockEm).close();
	}
	
	@Test
	public void when_id_is_int_return_null() {
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);

		AirportDao ad = new AirportDao(mockEmf);
		Airport returnedAirport = ad.getById(1);
		
		assertNull(returnedAirport);
	}
	
	@Test
	public void when_ID_is_String_getById_returns_Airport() {
		String airportIATAId = "MXP";
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		InOrder order = inOrder(mockEmf, mockEm, mockEt);
		
		AirportDao ad = new AirportDao(mockEmf);
		ad.getById(airportIATAId);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Airport.class, airportIATAId);
		order.verify(mockEm).close();
	}
}
