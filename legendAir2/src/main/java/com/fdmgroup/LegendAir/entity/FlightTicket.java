package com.fdmgroup.LegendAir.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="LA_FLIGHT_TICKET")
public class FlightTicket {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private int flightTicketId;

	@Column(nullable=false)
	private String flightClass;
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date flightDate;
	@Column(nullable=false)
	private double flightPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "bookingId" )
	private Booking laBooking;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "passengerId" )
	private Passenger laPassenger;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "flightId" )
	private Flight laFlight;

	public FlightTicket() {
	}

	public FlightTicket(String flightClass, Date flightDate, double flightPrice) {
		super();
		this.flightClass = flightClass;
		this.flightDate = flightDate;
		this.flightPrice = flightPrice;
	}

	public int getFlightTicketId() {
		return flightTicketId;
	}

	public void setFlightTicketId(int flightTicketId) {
		this.flightTicketId = flightTicketId;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	public double getFlightPrice() {
		return flightPrice;
	}

	public void setFlightPrice(double flightPrice) {
		this.flightPrice = flightPrice;
	}

	public Booking getBooking() {
		return laBooking;
	}

	public void setBooking(Booking laBooking) {
		this.laBooking = laBooking;
	}

	public void setPassenger(Passenger passenger) {
		this.laPassenger = passenger;
	}	

	public Passenger getPassenger(Passenger passenger) {
		return laPassenger;
	}

	public Flight getFlight() {
		return laFlight;
	}

	public void setFlight(Flight laFlight) {
		this.laFlight = laFlight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + flightTicketId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightTicket other = (FlightTicket) obj;
		if (flightTicketId != other.flightTicketId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FlightTicket [flightTicketId=" + flightTicketId + ", flightClass=" + flightClass + flightDate + ", flightPrice=" + flightPrice + "]";
	}
}
