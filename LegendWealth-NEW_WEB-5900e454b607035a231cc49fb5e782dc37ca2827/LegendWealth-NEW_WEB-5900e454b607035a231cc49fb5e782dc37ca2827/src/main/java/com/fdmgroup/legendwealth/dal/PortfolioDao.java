package com.fdmgroup.legendwealth.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.legendwealth.entity.Portfolio;

public class PortfolioDao extends GenericDao<Portfolio> {
	private EntityManagerFactory entityManagerFactory;

	public PortfolioDao(EntityManagerFactory entityManagerFactory) {
		super(Portfolio.class, entityManagerFactory);
		this.entityManagerFactory = entityManagerFactory;
	}
	
	

	public void addRelationship(Portfolio portfolio) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(portfolio);
		entityTransaction.commit();
		entityManager.close();
	}

}
