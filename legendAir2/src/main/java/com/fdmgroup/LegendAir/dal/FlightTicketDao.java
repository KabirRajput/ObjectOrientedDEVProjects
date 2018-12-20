package com.fdmgroup.LegendAir.dal;

import javax.persistence.EntityManagerFactory;

import com.fdmgroup.LegendAir.entity.FlightTicket;

public class FlightTicketDao extends GenericDao<FlightTicket> {
	public FlightTicketDao(EntityManagerFactory emf) {
		super(emf, FlightTicket.class);
	}
}
