package com.fdmgroup.legendwealth.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.legendwealth.entity.Asset;

public class AssetDao extends GenericDao<Asset> {
	private EntityManagerFactory entityManagerFactory;

	public AssetDao(EntityManagerFactory entityManagerFactory) {
		super(Asset.class, entityManagerFactory);
		this.entityManagerFactory = entityManagerFactory;
	}
	


}
