package com.fdmgroup.LegendAir.entity;

import java.util.*;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "LA_FLIGHT")
public class Flight {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private int flightId;
	@Column(nullable = false)
	private String departureTime;
	@Column(nullable = false)
	private String arrivalTime;
	@Column(nullable = false)
	private int capacity;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="laFlight")
	private List<FlightTicket> flightTickets = new ArrayList<FlightTicket>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "originId" )
	private Airport laOrigin;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "destinationId" )
	private Airport laDestination;

	public Flight() {
	}
	
	public Flight(String departureTime, String arrivalTime, int capacity) {
		super();
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.capacity = capacity;
	}
	
	public Flight(String departureTime, String arrivalTime, int capacity, Airport origin, Airport destination) {
		super();
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.capacity = capacity;
		this.laOrigin = origin;
		this.laDestination = destination;
	}

	public int getFlightId() {
		return flightId;
	}
	
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	
	public String getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	
	public String getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<FlightTicket> getFlightTickets() {
		return flightTickets;
	}

	public void setFlightTickets(List<FlightTicket> flightTickets) {
		this.flightTickets = flightTickets;
	}
	
    public Airport getOrigin() {
		return laOrigin;
	}

	public void setOrigin(Airport laOrigin) {
		this.laOrigin = laOrigin;
	}

	public Airport getDestination() {
		return laDestination;
	}

	public void setDestination(Airport laDestination) {
		this.laDestination = laDestination;
	}

	public void addFlightTicket(FlightTicket flightTicket) {
		flightTickets.add(flightTicket);
		flightTicket.setFlight(this);
    }
    
    public void removeFlightTicket(FlightTicket flightTicket) {
        flightTickets.remove(flightTicket);
        flightTicket.setFlight(null);
    }
	
	public void updateFlightTicketDate(FlightTicket flightTicket, String newDate) {
		for(FlightTicket f : flightTickets) {
			if(f.equals(flightTicket)) {
				flightTicket.setFlightDate(Date.valueOf(newDate));
				break;
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + flightId;
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
		Flight other = (Flight) obj;
		if (flightId != other.flightId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
				+ ", capacity=" + capacity + ", flightTickets=" + flightTickets + "]";
	}
}
