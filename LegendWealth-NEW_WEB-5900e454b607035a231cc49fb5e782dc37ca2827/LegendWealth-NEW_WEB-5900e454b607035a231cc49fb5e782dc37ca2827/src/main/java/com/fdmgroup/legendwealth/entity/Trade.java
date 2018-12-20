package com.fdmgroup.legendwealth.entity;

import javax.persistence.*;

import com.fdmgroup.legendwealth.dal.GenericDao;
import com.fdmgroup.legendwealth.dal.PortfolioDao;

@Entity
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "trade_id")
	private int tradeId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinColumn(name="broker_id")
	private Broker broker;

	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinColumn(name = "portfolio_id")
	private Portfolio portfolio;

	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinColumn(name = "asset_id")
	private Asset asset;

	private String transactionTime;
	private double quantity;
	private double priceTotal;

	public Trade(Broker broker, Portfolio portfolio, Asset asset, String transactionTime, double quantity,
			double priceTotal) {
		super();
		this.broker = broker;
		this.portfolio = portfolio;
		this.asset = asset;
		this.transactionTime = transactionTime;
		this.quantity = quantity;
		this.priceTotal = priceTotal;
	}
	

	public Trade() {
		// For JPA
	}

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}

//	@Override
//	public String toString() {
//		return "Trade [tradeId=" + tradeId + ", broker_id=" + broker.getBrokerId() + ", portfolio_id=" + portfolio.getPortfolioId() + ", asset_id=" + asset.getAssetId()
//				+ ", transactionTime=" + transactionTime + ", quantity=" + quantity + ", priceTotal=" + priceTotal
//				+ "]";
//	}

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + "]";
	}

}
