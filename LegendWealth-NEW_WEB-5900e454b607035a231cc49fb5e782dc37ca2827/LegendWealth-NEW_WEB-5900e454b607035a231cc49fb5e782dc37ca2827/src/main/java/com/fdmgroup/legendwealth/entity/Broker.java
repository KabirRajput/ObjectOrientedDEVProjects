package com.fdmgroup.legendwealth.entity;

import java.util.*;

import javax.persistence.*;

@Entity
public class Broker {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "broker_id")
	private long brokerId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;

	@OneToMany(mappedBy = "broker", cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
	private List<Trade> trades;

	@ManyToMany(mappedBy = "brokersWhoManage", fetch=FetchType.EAGER)
	private Set<Portfolio> portfoliosManaging = new HashSet<Portfolio>();

	public Broker(String firstName, String lastName, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public Broker() {
		// For JPA
	}
	
	public void addPortfolio(Portfolio p) {
		this.getPortfoliosManaging().add(p);
	}
	

	public long getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(long brokerId) {
		this.brokerId = brokerId;
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

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	public Set<Portfolio> getPortfoliosManaging() {
		return portfoliosManaging;
	}

	public void setPortfoliosManaging(Set<Portfolio> portfoliosManaging) {
		this.portfoliosManaging = portfoliosManaging;
	}

	@Override
	public String toString() {
		return "Broker [brokerId=" + brokerId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password +
				// ", trades=" + trades + 
				", portfoliosManaging=" + portfoliosManaging + "]";
	}


}
