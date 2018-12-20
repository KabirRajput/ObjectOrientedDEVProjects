package com.fdmgroup.legendwealth.dal;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.fdmgroup.legendwealth.entity.Broker;

public class BrokerDao extends GenericDao<Broker> {
	private EntityManagerFactory entityManagerFactory;

	public BrokerDao(EntityManagerFactory entityManagerFactory) {
		super(Broker.class, entityManagerFactory);
		this.entityManagerFactory = entityManagerFactory;
	}


	public Broker getBrokerByUsernameAndPassword(String username, String password) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Broker matchedBroker =  null;
		
		TypedQuery<Broker> query = entityManager.createQuery("SELECT b FROM Broker b WHERE b.username = :username AND b.password = :password", Broker.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		entityTransaction.begin();
		try {
			matchedBroker = (Broker) query.getSingleResult();
		}
		catch (NoResultException nre){
			//Ignore this because as per your logic this is ok, but we should log it
		}
		finally {			
			entityTransaction.commit();
			entityManager.close();
		}
		return matchedBroker;
	}
}
