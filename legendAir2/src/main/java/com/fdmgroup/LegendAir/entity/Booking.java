package com.fdmgroup.LegendAir.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="LA_BOOKING")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;
	private double bookingPrice;
	@Temporal(TemporalType.DATE)
	private Date dateOfBooking;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentMethodId")
	private PaymentMethod laPaymentMethod;
    
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="laBooking")
    private List<FlightTicket> flightTickets = new ArrayList<FlightTicket>();

    public Booking() {
	}
    
	public Booking(double bookingPrice, Date dateOfBooking, PaymentMethod paymentMethod) {
		super();
		this.bookingPrice = bookingPrice;
		this.dateOfBooking = dateOfBooking;
		this.laPaymentMethod = paymentMethod;
	}
    
	public Booking(double bookingPrice, Date dateOfBooking) {
		super();
		this.bookingPrice = bookingPrice;
		this.dateOfBooking = dateOfBooking;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public double getBookingPrice() {
		return bookingPrice;
	}

	public void setBookingPrice(double bookingPrice) {
		this.bookingPrice = bookingPrice;
	}

	public Date getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public PaymentMethod getPaymentMethod() {
		return laPaymentMethod;
	}

	public void setPaymentMethod(PaymentMethod laPaymentMethod) {
		this.laPaymentMethod = laPaymentMethod;
	}
	
	public List<FlightTicket> getFlightTickets() {
		return flightTickets;
	}

	public void setFlightTickets(List<FlightTicket> flightTickets) {
		this.flightTickets = flightTickets;
	}

	public void addFlightTicket(FlightTicket flightTicket) {
		flightTickets.add(flightTicket);
		flightTicket.setBooking(this);
	}
	
	public void removeFlightTicket(FlightTicket flightTicket) {
		flightTickets.remove(flightTicket);
		flightTicket.setBooking(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookingId;
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
		Booking other = (Booking) obj;
		if (bookingId != other.bookingId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", bookingPrice=" + bookingPrice + ", dateOfBooking=" + dateOfBooking
				+ "]";
	}
}
