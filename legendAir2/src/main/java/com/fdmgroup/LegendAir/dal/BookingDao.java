package com.fdmgroup.LegendAir.dal;

import java.util.*;

import javax.persistence.*;

import com.fdmgroup.LegendAir.entity.Booking;
import com.fdmgroup.LegendAir.entity.FlightTicket;

public class BookingDao extends GenericDao<Booking>{
	public BookingDao(EntityManagerFactory emf) {
		super(emf, Booking.class);
	}

	public void addFlightTicket(Booking booking, FlightTicket flightTicket) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Booking returnedBooking = em.find(Booking.class, booking.getBookingId());
		returnedBooking.addFlightTicket(flightTicket);
		et.commit();
		em.close();
	}

	public List<FlightTicket> getFlightTickets(Booking booking) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Booking returnedBooking = em.find(Booking.class, booking.getBookingId());
		List<FlightTicket> flightTickets = returnedBooking.getFlightTickets();
		et.commit();
		em.close();
		
		return flightTickets;
	}

	public void removeFlightTicket(Booking booking, FlightTicket flightTicket) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Booking returnedBooking = em.find(Booking.class, booking.getBookingId());
		returnedBooking.removeFlightTicket(flightTicket);
		et.commit();
		em.close();
	}
}
