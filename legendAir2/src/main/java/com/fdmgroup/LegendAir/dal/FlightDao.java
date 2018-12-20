package com.fdmgroup.LegendAir.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fdmgroup.LegendAir.entity.Flight;
import com.fdmgroup.LegendAir.entity.FlightTicket;

public class FlightDao extends GenericDao<Flight> {

	public FlightDao(EntityManagerFactory emf) {
		super(emf, Flight.class);
	}

	public void addFlightTicket(Flight flight, FlightTicket flightTicket) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Flight returnedFlight = em.find(Flight.class, flight.getFlightId());
		returnedFlight.addFlightTicket(flightTicket);
		et.commit();
		em.close();
	}
	
	public List<FlightTicket> getFlightTickets(Flight flight) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Flight returnedFlight = em.find(Flight.class, flight.getFlightId());
		List<FlightTicket> flightTickets = returnedFlight.getFlightTickets();
		et.commit();
		em.close();
		
		return flightTickets;
	}
	
	public void removeFlightTicket(Flight flight, FlightTicket flightTicket) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Flight returnedFlight = em.find(Flight.class, flight.getFlightId());
		returnedFlight.removeFlightTicket(flightTicket);
		et.commit();
		em.close();
	}

	public List<Object[]> getFlightTicketsByIdDate(String originIATAId, String destinationIATAId, String departureDate) {
		String queryString = "SELECT ft.flightTicketId, f.departureTime, f.arrivalTime, ft.flightPrice FROM Flight f INNER JOIN f.flightTickets ft WHERE f.laOrigin = '" + originIATAId + "' AND  f.laDestination = '" + destinationIATAId + "' AND ft.flightDate = '" + departureDate + "'";
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Query query = em.createQuery(queryString);
		List<Object[]> flightTicketResult = query.getResultList();
		em.close();
		return flightTicketResult;
	}
}
