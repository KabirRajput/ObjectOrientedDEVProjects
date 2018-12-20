package com.fdmgroup.legendwealth.dal;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public abstract class GenericDao<T> {
	private Class<T> className;
	private EntityManagerFactory entityManagerFactory;
	
	public GenericDao(Class<T> t, EntityManagerFactory entityManagerFactory) {
		this.className = t;
		this.entityManagerFactory = entityManagerFactory;
	}

	public void add(T t) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(t);
		entityTransaction.commit();
		entityManager.close();
	}
	
	public void merge(T t) {
		System.out.println("Inside Generic Dao: " + t);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(t);
		System.out.println("Merged.");
		entityTransaction.commit();
		entityManager.close();
	}

	public List<T> getList() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Query query = entityManager.createQuery("SELECT e FROM "+ className.getSimpleName() +" e");
		
		entityTransaction.begin();
		List<T> resultList = query.getResultList();
		entityTransaction.commit();
		entityManager.close();
		return resultList;
	}

	public T getById(long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		T ResultFromDataBase = entityManager.find(className, id);
		entityManager.close();
		return ResultFromDataBase;
	}
	
}
