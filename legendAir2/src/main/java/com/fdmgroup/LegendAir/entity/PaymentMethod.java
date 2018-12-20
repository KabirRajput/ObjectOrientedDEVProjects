package com.fdmgroup.LegendAir.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "LA_PAYMENT_METHOD")
public class PaymentMethod {

	@Id
	private String paymentMethodId;
	private String type;
	@Basic(optional=true)
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
	private User laUser;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "laPaymentMethod", fetch = FetchType.EAGER)
    private List<Booking> bookings = new ArrayList<Booking>();
    
    public PaymentMethod() {
	}
    
	public PaymentMethod(String paymentMethodId, String type) {
		super();
		this.paymentMethodId = paymentMethodId;
		this.type = type;
	}
    
	public PaymentMethod(String paymentMethodId, String type, User user) {
		super();
		this.paymentMethodId = paymentMethodId;
		this.type = type;
		this.laUser = user;
	}
	
	public PaymentMethod(String paymentMethodId, String type, Date expirationDate) {
		super();
		this.paymentMethodId = paymentMethodId;
		this.type = type;
		this.expirationDate = expirationDate;
	}

	public PaymentMethod(String paymentMethodId, String type, Date expirationDate, User user) {
		super();
		this.paymentMethodId = paymentMethodId;
		this.type = type;
		this.expirationDate = expirationDate;
		this.laUser = user;
	}

	public String getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public User getUser() {
		return laUser;
	}

	public void setUser(User user) {
		this.laUser = user;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public void addBooking(Booking booking) {
		bookings.add(booking);
		booking.setPaymentMethod(this);
	}
	
	public void removeBooking(Booking booking) {
		bookings.remove(booking);
		booking.setPaymentMethod(null);
	}
	
	@Override
	public String toString() {
		return "PaymentMethod [paymentMethodId=" + paymentMethodId + ", type=" + type + ", expirationDate="
				+ expirationDate + ", bookings=" + bookings + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paymentMethodId == null) ? 0 : paymentMethodId.hashCode());
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
		PaymentMethod other = (PaymentMethod) obj;
		if (paymentMethodId == null) {
			if (other.paymentMethodId != null)
				return false;
		} else if (!paymentMethodId.equals(other.paymentMethodId))
			return false;
		return true;
	}
}
