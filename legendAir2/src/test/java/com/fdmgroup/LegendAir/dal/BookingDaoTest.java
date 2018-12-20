package com.fdmgroup.LegendAir.dal;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import javax.persistence.*;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.entity.Booking;
import com.fdmgroup.LegendAir.entity.FlightTicket;
import com.fdmgroup.LegendAir.entity.User;

public class BookingDaoTest {
	@Test
	public void given_Booking_when_addingBookingToDB_then_userIsPersistedAndResourcesClosed() {
		Booking mockBooking = mock(Booking.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);

		BookingDao bDao = new BookingDao(mockEmf);
		bDao.add(mockBooking);
		InOrder order = inOrder(mockEmf, mockEm, mockEt);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).persist(mockBooking);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void DB_returnsNull_when_idDoesNotExist() {
		int id = 1000;

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Booking.class, id)).thenReturn(null);

		BookingDao bDao = new BookingDao(mockEmf);
		Booking expectedBooking = bDao.getById(id);

		assertNull(expectedBooking);
	}
	
	@Test
	public void given_existingBookingId_then_returnsBookingFromDB() {
		int id = 1000;

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);

		BookingDao bDao = new BookingDao(mockEmf);
		bDao.getById(id);

		InOrder order = inOrder(mockEmf, mockEm, mockEt);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Booking.class, id);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_query_then_ListOfBooking_is_returned_FromDB() {
		String queryString = "SELECT b FROM Booking b WHERE b.bookingPrice > 500.00";

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<Booking> mockQuery = mock(TypedQuery.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery(queryString, Booking.class)).thenReturn(mockQuery);

		BookingDao bDao = new BookingDao(mockEmf);
		bDao.getByQuery(queryString);

		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQuery);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).createQuery(queryString, Booking.class);
		order.verify(mockQuery).getResultList();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void existing_Booking_is_removed_from_db() {
		//arrange
		int id = 1000;
		
		Booking mockBooking = mock(Booking.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockBooking.getBookingId()).thenReturn(id);
		when(mockEm.find(Booking.class, id)).thenReturn(mockBooking);
		BookingDao bDao = new BookingDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockBooking);
		
		//act
		bDao.remove(mockBooking.getBookingId());
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Booking.class, mockBooking.getBookingId());
		order.verify(mockEm).remove(mockBooking);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void add_flightTicket_to_booking_then_persist_to_db() {
		//arrange
		int id = 66666666;
		
		Booking mockBooking = mock(Booking.class);
		FlightTicket mockFt = mock(FlightTicket.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockBooking.getBookingId()).thenReturn(id);
		when(mockEm.find(Booking.class, id)).thenReturn(mockBooking);
		BookingDao bDao = new BookingDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockBooking);
		
		//act
		bDao.addFlightTicket(mockBooking, mockFt);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Booking.class, mockBooking.getBookingId());
		order.verify(mockBooking).addFlightTicket(mockFt);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
		
	@Test
	public void	get_all_flightTickets_from_booking_method_then_resource_closed() {
		//arrange
		int id = 66666666;
		
		Booking mockBooking = mock(Booking.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockBooking.getBookingId()).thenReturn(id);
		when(mockEm.find(Booking.class, id)).thenReturn(mockBooking);
		BookingDao bDao = new BookingDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockBooking);
		
		//act
		bDao.getFlightTickets(mockBooking);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Booking.class, mockBooking.getBookingId());
		order.verify(mockBooking).getFlightTickets();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void remove_flightTicket_from_booking_then_change_persisted_to_db() {
		//arrange
		int id = 66666666;
		
		Booking mockBooking = mock(Booking.class);
		FlightTicket mockFt = mock(FlightTicket.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockBooking.getBookingId()).thenReturn(id);
		when(mockEm.find(Booking.class, id)).thenReturn(mockBooking);
		BookingDao bDao = new BookingDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockBooking);
		
		//act
		bDao.removeFlightTicket(mockBooking, mockFt);
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(Booking.class, mockBooking.getBookingId());
		order.verify(mockBooking).removeFlightTicket(mockFt);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
}
