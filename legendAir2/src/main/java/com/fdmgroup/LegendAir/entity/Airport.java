package com.fdmgroup.LegendAir.entity;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name = "LA_AIRPORT")
public class Airport {

	@Id
	private String airportIATAId;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String country;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="laOrigin")
	private List<Flight> flightOrigins = new ArrayList<Flight>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="laDestination")
	private List<Flight> flightDestinations = new ArrayList<Flight>();

	public Airport() {
	}

	public Airport(String airportIATAId, String name, String city, String country) {
		super();
		this.airportIATAId = airportIATAId;
		this.name = name;
		this.city = city;
		this.country = country;
	}
	
	public String getAirportIATAId() {
		return airportIATAId;
	}
	
	public void setAirportIATAId(String airportIATAId) {
		this.airportIATAId = airportIATAId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	public List<Flight> getFlightOrigins() {
		return flightOrigins;
	}

	public void setFlightOrigins(List<Flight> flightOrigins) {
		this.flightOrigins = flightOrigins;
	}

	public List<Flight> getFlightDestinations() {
		return flightDestinations;
	}

	public void setFlightDestinations(List<Flight> flightDestinations) {
		this.flightDestinations = flightDestinations;
	}
	
	public void addFlightToOrigin(Flight flight) {
		flightOrigins.add(flight);
		flight.setOrigin(this);
	}
	
	public void removeFlightFromOrigin(Flight flight) {
		flightOrigins.remove(flight);
		flight.setOrigin(null);
	}
	
	public void addFlightToDestination(Flight flight) {
		flightDestinations.add(flight);
		flight.setDestination(this);
	}
	
	public void removeFlightFromDestination(Flight flight) {
		flightDestinations.remove(flight);
		flight.setDestination(null);
	}

	@Override
	public String toString() {
		return "Airport [airportIATAId=" + airportIATAId + ", name=" + name + ", city=" + city + ", country=" + country
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airportIATAId == null) ? 0 : airportIATAId.hashCode());
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
		Airport other = (Airport) obj;
		if (airportIATAId == null) {
			if (other.airportIATAId != null)
				return false;
		} else if (!airportIATAId.equals(other.airportIATAId))
			return false;
		return true;
	}
	
}
