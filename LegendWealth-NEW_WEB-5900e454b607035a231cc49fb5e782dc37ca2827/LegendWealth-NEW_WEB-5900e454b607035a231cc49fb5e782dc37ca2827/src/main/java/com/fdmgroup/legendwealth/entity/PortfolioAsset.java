package com.fdmgroup.legendwealth.entity;

public class PortfolioAsset {

	private long portfolioId;
	private String portfolioName;
	private long assetId;
	private String assetCode;
	private String assetDescription;
	private double unitPrice;
	private double quantity;
	private double value;

	public PortfolioAsset(long portfolioId, String portfolioName, long assetId, String assetCode,
			String assetDescription, double unitPrice, double quantity, double value) {
		super();
		this.portfolioId = portfolioId;
		this.portfolioName = portfolioName;
		this.assetId = assetId;
		this.assetCode = assetCode;
		this.assetDescription = assetDescription;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.value = value;
	}

	public long getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(long portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public long getAssetId() {
		return assetId;
	}

	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetDescription() {
		return assetDescription;
	}

	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
