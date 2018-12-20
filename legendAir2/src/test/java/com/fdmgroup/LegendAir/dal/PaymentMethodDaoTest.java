package com.fdmgroup.LegendAir.dal;

import org.junit.Test;

import com.fdmgroup.LegendAir.entity.Booking;
import com.fdmgroup.LegendAir.entity.PaymentMethod;

import javax.persistence.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Date;

import org.mockito.InOrder;

public class PaymentMethodDaoTest {

	@Test
	public void given_paymentMethod_persist_toDB_then_resource_closed() {
		//arrange
		PaymentMethod mockPm = mock(PaymentMethod.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		PaymentMethodDao pmDao = new PaymentMethodDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt);
		
		//act
		pmDao.add(mockPm);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).persist(mockPm);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_existing_paymentMethodId_getById_from_db_and_then_paymentMethod_returned() {
		//arrange
		String id = "100";
	
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		PaymentMethodDao pmDao = new PaymentMethodDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt);
		
		//act
		pmDao.getById(id);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(PaymentMethod.class, id);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_nonExisting_paymentMethodId_getById_from_db_returns_null() {
		//arrange
		String id = "6666-6666-6666-6666";
	
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(PaymentMethod.class, id)).thenReturn(null);
		PaymentMethodDao pmDao = new PaymentMethodDao(mockEmf);
		
		//act
		PaymentMethod pm = pmDao.getById(id);
		
		//assert
		assertNull(pm);
	}
	
	@Test
	public void given_query_then_ListOfPaymentMethod_is_returned_FromDB() {
		String type =  "credit card";
		String queryString = "SELECT p FROM PaymentMethod p WHERE p.type = '" + type + "'";

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<PaymentMethod> mockQuery = mock(TypedQuery.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery(queryString, PaymentMethod.class)).thenReturn(mockQuery);

		PaymentMethodDao pDao = new PaymentMethodDao(mockEmf);
		pDao.getByQuery(queryString);

		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQuery);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).createQuery(queryString, PaymentMethod.class);
		order.verify(mockQuery).getResultList();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_existing_paymentMethod_update_type_and_persist_to_db_then_resource_closed() {
		//arrange
		String id = "6666-6666-6666-6666";
		Date date = Date.valueOf("2028-08-01");
		
		PaymentMethod mockPm = mock(PaymentMethod.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockPm.getPaymentMethodId()).thenReturn(id);
		when(mockEm.find(PaymentMethod.class, id)).thenReturn(mockPm);
		PaymentMethodDao pmDao = new PaymentMethodDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockPm);
		
		//act
		pmDao.updatePaymentMethodExpirationDate(mockPm, date);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(PaymentMethod.class, mockPm.getPaymentMethodId());
		order.verify(mockPm).setExpirationDate(date);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void existing_paymentMethod_is_removed_from_db_then_resource_closed() {
		//arrange
		String id = "6666-6666-6666-6666";
		
		PaymentMethod mockPm = mock(PaymentMethod.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockPm.getPaymentMethodId()).thenReturn(id);
		when(mockEm.find(PaymentMethod.class, id)).thenReturn(mockPm);
		PaymentMethodDao pmDao = new PaymentMethodDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockPm);
		
		//act
		pmDao.remove(mockPm.getPaymentMethodId());
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(PaymentMethod.class, mockPm.getPaymentMethodId());
		order.verify(mockEm).remove(mockPm);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void add_booking_to_payment_method_then_persist_to_db() {
		//arrange
		String id = "6666-6666-6666-6666";
		
		Booking mockBooking = mock(Booking.class);
		PaymentMethod mockPm = mock(PaymentMethod.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockPm.getPaymentMethodId()).thenReturn(id);
		when(mockEm.find(PaymentMethod.class, id)).thenReturn(mockPm);
		PaymentMethodDao pmDao = new PaymentMethodDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockPm);
		
		//act
		pmDao.addBooking(mockPm, mockBooking);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(PaymentMethod.class, mockPm.getPaymentMethodId());
		order.verify(mockPm).addBooking(mockBooking);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void	get_all_bookings_from_payment_method_then_resource_closed() {
		//arrange
		String id = "6666-6666-6666-6666";

		PaymentMethod mockPm = mock(PaymentMethod.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockPm.getPaymentMethodId()).thenReturn(id);
		when(mockEm.find(PaymentMethod.class, id)).thenReturn(mockPm);
		PaymentMethodDao pmDao = new PaymentMethodDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockPm);
		
		//act
		pmDao.getBookings(mockPm);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(PaymentMethod.class, mockPm.getPaymentMethodId());
		order.verify(mockPm).getBookings();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void remove_booking_from_db_then_change_persisted_to_db() {
		//arrange
		String id = "6666-6666-6666-6666";

		Booking mockBooking = mock(Booking.class);
		PaymentMethod mockPm = mock(PaymentMethod.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockPm.getPaymentMethodId()).thenReturn(id);
		when(mockEm.find(PaymentMethod.class, id)).thenReturn(mockPm);
		PaymentMethodDao pmDao = new PaymentMethodDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockPm);
		
		//act
		pmDao.removeBooking(mockPm, mockBooking);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(PaymentMethod.class, mockPm.getPaymentMethodId());
		order.verify(mockPm).removeBooking(mockBooking);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
}
