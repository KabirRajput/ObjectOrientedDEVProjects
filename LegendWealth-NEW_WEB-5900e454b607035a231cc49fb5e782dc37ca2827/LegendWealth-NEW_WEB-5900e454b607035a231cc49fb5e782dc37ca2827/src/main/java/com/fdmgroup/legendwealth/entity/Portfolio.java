package com.fdmgroup.legendwealth.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Portfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "portfolio_id")
	private long portfolioId;
	private String name;
	private double amount;

	@OneToMany(mappedBy = "portfolio", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<Trade> trades;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Broker_Portfolio", joinColumns = @JoinColumn(name = "portfolio_id"), inverseJoinColumns = @JoinColumn(name = "broker_id"))
	private Set<Broker> brokersWhoManage = new HashSet<Broker>();

//	@OneToMany(mappedBy = "id.portfolio", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//	private Set<PortfolioAsset> assetsInPortfolio;

	public Portfolio() {
		// For JPA
	}

	public Portfolio(String name, double amount) {
		super();
		this.name = name;
		this.amount = amount;
	}

	public Portfolio(long portfolioId, String name, double amount) {
		super();
		this.portfolioId = portfolioId;
		this.name = name;
		this.amount = amount;
	}

	public void addBroker(Broker b) {
		this.getBrokersWhoManage().add(b);
	}

	public long getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(long portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Set<Broker> getBrokersWhoManage() {
		return brokersWhoManage;
	}

	public void setBrokersWhoManage(Set<Broker> brokersWhoManage) {
		this.brokersWhoManage = brokersWhoManage;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	@Override
	public String toString() {
		return "Portfolio [portfolioId=" + portfolioId + ", name=" + name + ", amount=" + amount + ", trades=" + trades
				+ ", brokersWhoManage=" + brokersWhoManage + "]";
	}

//	public Set<PortfolioAsset> getAssetsInPortfolio() {
//		return assetsInPortfolio;
//	}
//
//	public void setAssetsInPortfolio(Set<PortfolioAsset> assetsInPortfolio) {
//		this.assetsInPortfolio = assetsInPortfolio;
//	}



}
