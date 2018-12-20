package com.fdmgroup.LegendAir.dal;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import com.fdmgroup.LegendAir.entity.Booking;
import com.fdmgroup.LegendAir.entity.PaymentMethod;

public class PaymentMethodDao extends GenericDao<PaymentMethod> {
	
	public PaymentMethodDao(EntityManagerFactory emf) {
		super(emf, PaymentMethod.class);
	}

	public PaymentMethod getById(String id) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		PaymentMethod returnedPaymentMethod = em.find(PaymentMethod.class, id);
		et.commit();
		em.close();

		return returnedPaymentMethod;
	}

	public void updatePaymentMethodExpirationDate(PaymentMethod paymentMethod, Date date) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		PaymentMethod returnedPaymentMethod = em.find(PaymentMethod.class, paymentMethod.getPaymentMethodId());
		returnedPaymentMethod.setExpirationDate(date);
		et.commit();
		em.close();
	}

	public void remove(String paymentMethodId) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		PaymentMethod returnedPaymentMethod = em.find(PaymentMethod.class, paymentMethodId);
		em.remove(returnedPaymentMethod);
		et.commit();
		em.close();
	}

	public void addBooking(PaymentMethod paymentMethod, Booking booking) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		PaymentMethod returnedPaymentMethod = em.find(PaymentMethod.class, paymentMethod.getPaymentMethodId());
		returnedPaymentMethod.addBooking(booking);
		et.commit();
		em.close();
	}

	public List<Booking> getBookings(PaymentMethod paymentMethod) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		PaymentMethod returnedPaymentMethod = em.find(PaymentMethod.class, paymentMethod.getPaymentMethodId());
		List<Booking> bookings = returnedPaymentMethod.getBookings();
		
		et.commit();
		em.close();
		
		returnedPaymentMethod.setBookings(bookings);
		
		return returnedPaymentMethod.getBookings();
	}

	public void removeBooking(PaymentMethod paymentMethod, Booking booking) {
		EntityManager em = this.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		PaymentMethod returnedPaymentMethod = em.find(PaymentMethod.class, paymentMethod.getPaymentMethodId());
		returnedPaymentMethod.removeBooking(booking);
		et.commit();
		em.close();
	}
}
