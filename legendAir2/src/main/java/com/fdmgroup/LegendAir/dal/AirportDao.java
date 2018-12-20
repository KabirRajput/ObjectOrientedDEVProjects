package com.fdmgroup.LegendAir.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fdmgroup.LegendAir.entity.Airport;
import com.fdmgroup.LegendAir.entity.Flight;

public class AirportDao extends GenericDao<Airport> {

	public AirportDao(EntityManagerFactory emf) {
		super(emf, Airport.class);
	}
	
	public Airport getById(int id) {
		return null;
	}
	
	public Airport getById(String airportIATAId) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Airport returnedAirport = em.find(Airport.class, airportIATAId);
		et.commit();
		em.close();
		
		return returnedAirport;
	}
	
	public List<Object[]> getByCity(String city) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		String queryString = "SELECT a.name, a.city, a.airportIATAId FROM Airport a WHERE a.city LIKE '" + city + "%'";
		et.begin();
		Query query = em.createQuery(queryString);
		List<Object[] > airportList = query.getResultList();
		et.commit();
		em.close();
		
		return airportList;
	}

	public void addFlightToOrigin(Airport airport, Flight flight) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Airport returnedAirport = em.find(Airport.class, airport.getAirportIATAId());
		returnedAirport.addFlightToOrigin(flight);
		et.commit();
		em.close();
	}

	public void addFlightToDestination(Airport airport, Flight flight) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Flight returnedFlight = em.find(Flight.class, flight.getFlightId());
		Airport returnedAirport = em.find(Airport.class, airport.getAirportIATAId());
		returnedAirport.addFlightToDestination(returnedFlight);
		et.commit();
		em.close();
	}
	
	public List<Flight> getFlightOrigins(Airport airport) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Airport returnedAirport = em.find(Airport.class, airport.getAirportIATAId());
		List<Flight> flightOrigins = returnedAirport.getFlightOrigins();
		et.commit();
		em.close();
		
		return flightOrigins;
	}
	
	public List<Flight> getFlightDestinations(Airport airport) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Airport returnedAirport = em.find(Airport.class, airport.getAirportIATAId());
		List<Flight> flightDestinations = returnedAirport.getFlightDestinations();
		et.commit();
		em.close();
		
		return flightDestinations;
	}
	
	public void removeFlightFromOrigin(Airport airport, Flight flight) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Airport returnedAirport = em.find(Airport.class, airport.getAirportIATAId());
		returnedAirport.removeFlightFromOrigin(flight);
		et.commit();
		em.close();
	}
	
	public void removeFlightFromDestination(Airport airport, Flight flight) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Airport returnedAirport = em.find(Airport.class, airport.getAirportIATAId());
		returnedAirport.removeFlightFromDestination(flight);
		et.commit();
		em.close();
	}
}
