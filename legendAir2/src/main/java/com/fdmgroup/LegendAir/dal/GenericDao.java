package com.fdmgroup.LegendAir.dal;

import java.util.*;

import javax.persistence.*;

import com.fdmgroup.LegendAir.entity.User;

public abstract class GenericDao<T> {
	private Class<T> type;
	private EntityManagerFactory emf;
	
	public GenericDao(EntityManagerFactory emf, Class<T> type) {
		this.emf = emf;
		this.type = type;
	}
	
	public void add(T t) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(t);
		et.commit();
		em.close();
	}
	
	public T getById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		T returned = em.find(type, id);
		et.commit();
		em.close();
		
		return returned;
	}
	
	public List<T> getByQuery(String queryString) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Query query = em.createQuery(queryString, type);
		List<T> returnedList = query.getResultList();
		et.commit();
		em.close();

		return returnedList;
	}
	
	public void remove(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		T returned= em.find(type, id);
		em.remove(returned);
		et.commit();
		em.close();
	}

	public Class<T> getType() {
		return type;
	}

	protected EntityManagerFactory getEmf() {
		return emf;
	}
}
