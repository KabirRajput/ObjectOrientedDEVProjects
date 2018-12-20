package com.fdmgroup.legendwealth.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "Asset_type")
public class AssetType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "asset_type_id")
	private long assetTypeId;
	private String description;

	@OneToMany(mappedBy = "assetType",cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private List<Asset> assets;

	public AssetType(String description) {
		super();
		this.description = description;
	}

	public AssetType() {

	}

	public long getAssetTypeId() {
		return assetTypeId;
	}

	public void setAssetTypeId(int assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Asset_Type [assetTypeId=" + assetTypeId + ", description=" + description + "]";
	}

}
