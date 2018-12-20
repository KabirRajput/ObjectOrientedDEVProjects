package com.fdmgroup.legendwealth.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Asset {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "asset_id")
	private long assetId;
	private String code;
	private double price;

	@OneToMany(mappedBy = "asset", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<Trade> trades;

	@ManyToOne
	@JoinColumn(name = "asset_type_id")
	private AssetType assetType;
	
//	@OneToMany(mappedBy = "id.asset", cascade = CascadeType.MERGE)
//	private List<PortfolioAsset> portfoliosShared;

	public Asset(String code, double price, AssetType assetType) {
		super();
		this.code = code;
		this.price = price;
		this.assetType = assetType;
	}

	public Asset() {

	}

	public long getAssetId() {
		return assetId;
	}

	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	@Override
	public String toString() {
		return "Asset [assetId=" + assetId + ", code=" + code + ", price=" + price + ", trades=" + trades
				+ ", assetType=" + assetType + "]";
	}

//	public List<PortfolioAsset> getPortfoliosShared() {
//		return portfoliosShared;
//	}
//
//	public void setPortfoliosShared(List<PortfolioAsset> portfoliosShared) {
//		this.portfoliosShared = portfoliosShared;
//	}
	


}
