package com.fdmgroup.LegendAir.dal;

import java.util.*;

import javax.persistence.*;

import com.fdmgroup.LegendAir.entity.Passenger;
import com.fdmgroup.LegendAir.entity.PaymentMethod;
import com.fdmgroup.LegendAir.entity.User;

public class UserDao extends GenericDao<User> {
	
	public UserDao(EntityManagerFactory emf) {
		super(emf, User.class);
	}

	public User getByUsername(String username) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		User returnedUser = null; 
		Query query = em.createQuery(
				"SELECT u FROM User u WHERE u.username = '" + username + "'", User.class);
		try {
			returnedUser = (User) query.getSingleResult();
		} catch(NoResultException e) {
			// Log later
		} 

		et.commit();
		em.close();

		return returnedUser;
	}

	public void updatePassword(User user, String newPassword) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		User returnedUser = em.find(User.class, user.getUserId());
		returnedUser.setPassword(newPassword);
		et.commit();
		em.close();
	}

	public void addPaymentMethod(User user, PaymentMethod paymentMethod) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		User returnedUser = em.find(User.class, user.getUserId());
		returnedUser.addPaymentMethod(paymentMethod);
		et.commit();
		em.close();
	}
	
	public List<PaymentMethod> getPaymentMethods(User user) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		User returnedUser = em.find(User.class, user.getUserId());
		List<PaymentMethod> paymentMethods = returnedUser.getPaymentMethods();
		et.commit();
		em.close();
		return paymentMethods;
	}
	
	public void updatePaymentMethodExpirationDate(User user, PaymentMethod paymentMethod, String date) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		User returnedUser = em.find(User.class, user.getUserId());
		returnedUser.updatePaymentMethodExpirationDate(paymentMethod, date);
		et.commit();
		em.close();
	}

	public void removePaymentMethod(User user, PaymentMethod paymentMethod) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		User returnedUser = em.find(User.class, user.getUserId());
		returnedUser.removePaymentMethod(paymentMethod);
		et.commit();
		em.close();
	}
	
	public String verifyCredentials(String username, String password) {
		User user = getByUsername(username);
		if(user == null) {
			return "Username was not found!";
		}
		
		if(!user.getPassword().equals(password)) {
			return "Password is not valid!";
		}
		
		return "true";
	}
	
	public void addPassenger(String username, Passenger passenger) {
		String queryString = "SELECT u FROM User u WHERE u.username = '" + username + "'";
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Query query = em.createQuery(queryString);
		User user = (User) query.getSingleResult();
		user.addPassenger(passenger);
		et.commit();
		em.close();
	}
	
	public List<String[]> getPassengers(String username) {
		String queryString = "SELECT p.lastName, p.firstName, p.mobile, p.passportNo from User u LEFT JOIN u.passengers p WHERE u.username = '" + username + "'";
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Query query = em.createQuery(queryString);
		List<String[]> passengersData = query.getResultList();
		et.commit();
		em.close();
		
		return passengersData;
	}
}
