package com.fdmgroup.LegendAir.dal;

import java.util.*;

import javax.persistence.*;

import com.fdmgroup.LegendAir.entity.FlightTicket;
import com.fdmgroup.LegendAir.entity.Passenger;

public class PassengerDao extends GenericDao<Passenger>{

	public PassengerDao(EntityManagerFactory emf) {
		super(emf, Passenger.class);
	}

	public void addFlightTicket(Passenger passenger, FlightTicket flightTicket) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Passenger returnedPassenger = em.find(Passenger.class, passenger.getPassengerId());
		returnedPassenger.addFlightTicket(flightTicket);
		et.commit();
		em.close();
	}

	public List<FlightTicket> getFlightTickets(Passenger passenger) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Passenger returnedPassenger = em.find(Passenger.class, passenger.getPassengerId());
		List<FlightTicket> flightTickets = returnedPassenger.getFlightTickets();
		et.commit();
		em.close();
		
		return flightTickets;
	}

	public void removeFlightTicket(Passenger passenger, FlightTicket flightTicket) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Passenger returnedPassenger = em.find(Passenger.class, passenger.getPassengerId());
		returnedPassenger.removeFlightTicket(flightTicket);
		et.commit();
		em.close();
	}

	
}
