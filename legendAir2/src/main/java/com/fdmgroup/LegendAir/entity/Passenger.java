package com.fdmgroup.LegendAir.entity;

import java.util.*;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "LA_PASSENGER")
public class Passenger {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int passengerId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String mobile;
	@Column(nullable = false)
	private String passportNo;
	@Column(name = "OWNER_ID", insertable = false, updatable = false)
	private int userId;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="laPassenger")
	private List<FlightTicket> flightTickets = new ArrayList<FlightTicket>();

	public Passenger() {
	}

	public Passenger(String firstName, String lastName, String passportNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.passportNo = passportNo;
	}

	public Passenger(String firstName, String lastName, String mobile, String passportNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.passportNo = passportNo;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void addFlightTicket(FlightTicket flightTicket) {
		flightTickets.add(flightTicket);
		flightTicket.setPassenger(this);
	}

	public void removeFlightTicket(FlightTicket flightTicket) {
		flightTickets.remove(flightTicket);
		flightTicket.setPassenger(null);
	}

	public List<FlightTicket> getFlightTickets() {
		return flightTickets;
	}

	public void setFlightTickets(List<FlightTicket> flightTickets) {
		this.flightTickets = flightTickets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + passengerId;
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
		Passenger other = (Passenger) obj;
		if (passengerId != other.passengerId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobile=" + mobile + ", passportNo=" + passportNo + "]";
	}
}
