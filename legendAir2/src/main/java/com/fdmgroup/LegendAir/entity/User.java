package com.fdmgroup.LegendAir.entity;

import java.util.*;
import java.sql.Date;

import javax.persistence.*;


@Entity
@Table(name = "LA_USER")
public class User {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="USER_ID")
	private int userId;
	@Column(unique=true, nullable=false)
	private String username;
	@Column(nullable = false)
	private String password;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="laUser", fetch = FetchType.EAGER)
	private List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="OWNER_ID", referencedColumnName="USER_ID", nullable = false)
	private Set<Passenger> passengers = new HashSet<Passenger>();

	public User() {
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethod(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
	
    public Set<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(Set<Passenger> passengers) {
		this.passengers = passengers;
	}

	public void addPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.add(paymentMethod);
        paymentMethod.setUser(this);
    }
   
    public void removePaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.remove(paymentMethod);
        paymentMethod.setUser(null);
    }
    
    public void updatePaymentMethodExpirationDate(PaymentMethod paymentMethod, String newExpirationDate) {
    	for(PaymentMethod pm : paymentMethods) {
    		if(pm.equals(paymentMethod)) {
    			pm.setExpirationDate(Date.valueOf(newExpirationDate));
    		}
    	}
    }
    
    public void addPassenger(Passenger passenger) {
    	passengers.add(passenger);
    }
    
    public void updatePassenger(Passenger passenger) {
    	for(Passenger p : passengers) {
    		if(p.equals(passenger)) {
    			p = passenger;
    		}
    	}
    }
    
    public void removePassenger(Passenger passenger) {
    	passengers.remove(passenger);
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + "]";
	}
    
}
