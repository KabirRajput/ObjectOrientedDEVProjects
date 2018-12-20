package com.fdmgroup.legendwealth.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.legendwealth.entity.AssetType;

public class AssetTypeDao extends GenericDao<AssetType> {
	private EntityManagerFactory entityManagerFactory;

	public AssetTypeDao(EntityManagerFactory entityManagerFactory) {
		super(AssetType.class, entityManagerFactory);
		this.entityManagerFactory = entityManagerFactory;
	}
	


}
