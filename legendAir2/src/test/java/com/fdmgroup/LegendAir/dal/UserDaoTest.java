package com.fdmgroup.LegendAir.dal;

import javax.persistence.*;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.dal.UserDao;
import com.fdmgroup.LegendAir.entity.Passenger;
import com.fdmgroup.LegendAir.entity.PaymentMethod;
import com.fdmgroup.LegendAir.entity.User;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserDaoTest {
	
	@Test
	public void given_user_when_adding_to_db_then_is_persisted_and_resource_closed() {
		User mockUser = mock(User.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		UserDao userDao = new UserDao(mockEmf);
		userDao.add(mockUser);
		InOrder order = inOrder(mockEmf, mockEm, mockEt);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).persist(mockUser);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}

	@Test
	public void given_User_when_addingUserToDB_then_userIsPersistedAndResourcesClosed() {
		User mockUser = mock(User.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);

		UserDao userDao = new UserDao(mockEmf);
		userDao.add(mockUser);
		InOrder order = inOrder(mockEmf, mockEm, mockEt);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).persist(mockUser);
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
		when(mockEm.find(User.class, id)).thenReturn(null);

		UserDao userDao = new UserDao(mockEmf);
		User expectedUser = userDao.getById(id);

		assertNull(expectedUser);
	}
	
	@Test
	public void given_existingUserId_then_returnsUserFromDB() {
		int id = 1000;

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);

		UserDao userDao = new UserDao(mockEmf);
		userDao.getById(id);

		InOrder order = inOrder(mockEmf, mockEm, mockEt);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(User.class, id);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_query_then_ListOfUser_is_returned_FromDB() {
		String username =  "andrew";
		String queryString = "SELECT u FROM User u WHERE u.username = '" + username + "'";

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<User> mockQuery = mock(TypedQuery.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery(queryString, User.class)).thenReturn(mockQuery);

		UserDao userDao = new UserDao(mockEmf);
		userDao.getByQuery(queryString);

		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQuery);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).createQuery(queryString, User.class);
		order.verify(mockQuery).getResultList();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void DB_returnsNull_when_usernameDoesNotExist() {
		String username =  "andrew";

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<User> mockQuery = mock(TypedQuery.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT u FROM User u WHERE u.username = '" + username + "'", User.class)).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(null);

		UserDao userDao = new UserDao(mockEmf);
		User expectedUser = userDao.getByUsername(username);
		
		assertNull(expectedUser);
	}

	@Test
	public void given_existingUsername_then_returnsUserFromDB() {
		String username =  "andrew";
		User mockUser = mock(User.class);

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<User> mockQuery = mock(TypedQuery.class);
		
		when(mockUser.getUsername()).thenReturn(username);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT u FROM User u WHERE u.username = '" + username + "'", User.class)).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(mockUser);

		UserDao userDao = new UserDao(mockEmf);
		userDao.getByUsername(username);

		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQuery);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).createQuery("SELECT u FROM User u WHERE u.username = '" + username + "'", User.class);
		order.verify(mockQuery).getSingleResult();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_non_existing_username_null_is_returned_from_DB() {
		String username = "doNotExist";
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<User> mockQuery = mock(TypedQuery.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT u FROM User u WHERE u.username = '" + username + "'", User.class)).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(null);
		
		UserDao userDao = new UserDao(mockEmf);
		User user = userDao.getByUsername(username);
		
		assertNull(user);
	}
	
	@Test
	public void update_existingUserPassword_byFindingById_fromDB() {
		int id = 1000;
		String newPassword = "password";
		User mockUser = mock(User.class);

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockUser.getUserId()).thenReturn(id);
		when(mockEm.find(User.class, mockUser.getUserId())).thenReturn(mockUser);

		UserDao userDao = new UserDao(mockEmf);
		userDao.updatePassword(mockUser, newPassword);

		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockUser);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(User.class, mockUser.getUserId());
		order.verify(mockUser).setPassword(newPassword);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void existing_user_is_removed_from_db() {
		//arrange
		int id = 1000;
		
		User mockUser = mock(User.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockUser.getUserId()).thenReturn(id);
		when(mockEm.find(User.class, id)).thenReturn(mockUser);
		UserDao userDao = new UserDao(mockEmf);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockUser);
		
		//act
		userDao.remove(mockUser.getUserId());
		
		//assert
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(User.class, mockUser.getUserId());
		order.verify(mockEm).remove(mockUser);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void add_OnePaymentMethod_toUser() {
		int id = 1000;
		
		User mockUser = mock(User.class);
		PaymentMethod mockPaymentMethod = mock(PaymentMethod.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockUser.getUserId()).thenReturn(id);
		when(mockEm.find(User.class, mockUser.getUserId())).thenReturn(mockUser);

		UserDao userDao = new UserDao(mockEmf);
		userDao.addPaymentMethod(mockUser, mockPaymentMethod);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockUser);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(User.class, mockUser.getUserId());
		order.verify(mockUser).addPaymentMethod(mockPaymentMethod);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void get_allPaymentMethods_ofUser() {
		int id = 1000;
		
		User mockUser = mock(User.class);
		List<PaymentMethod> mockPm = mock(List.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockUser.getUserId()).thenReturn(id);
		when((mockEm).find(User.class, mockUser.getUserId())).thenReturn(mockUser);
		when(mockUser.getPaymentMethods()).thenReturn(mockPm);
		
		UserDao userDao = new UserDao(mockEmf);
		userDao.getPaymentMethods(mockUser);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockUser);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(User.class, mockUser.getUserId());
		order.verify(mockUser).getPaymentMethods();
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void update_existingPaymentMethod_inUser() {
		int id = 1000;
		String newExpirationDate = "2025-05-20";
		
		User mockUser = mock(User.class);
		PaymentMethod mockPaymentMethod = mock(PaymentMethod.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockUser.getUserId()).thenReturn(id);
		when((mockEm).find(User.class, mockUser.getUserId())).thenReturn(mockUser);
		
		
		UserDao userDao = new UserDao(mockEmf);
		userDao.updatePaymentMethodExpirationDate(mockUser, mockPaymentMethod, newExpirationDate);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockUser);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(User.class, mockUser.getUserId());
		order.verify(mockUser).updatePaymentMethodExpirationDate(mockPaymentMethod, newExpirationDate);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void remove_existingPaymentMethod_inUser() {
		int id = 1000;
		
		User mockUser = mock(User.class);
		PaymentMethod mockPaymentMethod = mock(PaymentMethod.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockUser.getUserId()).thenReturn(id);
		when(mockEm.find(User.class, mockUser.getUserId())).thenReturn(mockUser);

		UserDao userDao = new UserDao(mockEmf);
		userDao.removePaymentMethod(mockUser, mockPaymentMethod);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockUser);

		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).find(User.class, mockUser.getUserId());
		order.verify(mockUser).removePaymentMethod(mockPaymentMethod);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_existing_username_and_correct_password_true_is_returned() {
		String username = "andrew";
		String password = "pass";
		
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		User mockUser = mock(User.class);
		UserDao ud  = new UserDao(mockEmf);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<User> mockQuery = mock(TypedQuery.class);
		
		when(mockUser.getUsername()).thenReturn(username);
		when(mockUser.getPassword()).thenReturn(password);
		when(mockUser.getUsername()).thenReturn(username);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT u FROM User u WHERE u.username = '" + username + "'", User.class)).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(mockUser);
		
		String result = ud.verifyCredentials(username, password);
		assertEquals("true", result);
	}
	
	@Test
	public void given_existing_username_and_wrong_password_password_not_valid_is_returned() {
		String username = "andrew";
		String userPassword = "password";
		String password = "wrong pass";
		
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		User mockUser = mock(User.class);
		UserDao ud  = new UserDao(mockEmf);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<User> mockQuery = mock(TypedQuery.class);
		
		when(mockUser.getUsername()).thenReturn(username);
		when(mockUser.getPassword()).thenReturn(userPassword);
		when(mockUser.getUsername()).thenReturn(username);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT u FROM User u WHERE u.username = '" + username + "'", User.class)).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(mockUser);
		
		String result = ud.verifyCredentials(username, password);
		assertEquals("Password is not valid!", result);
	}
	
	@Test
	public void given_non_existing_username_not_found_is_returned() {
		String nonExistingUsername = "trollol";
		String password = "pass";
		
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		User mockUser = mock(User.class);
		UserDao ud  = new UserDao(mockEmf);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery<User> mockQuery = mock(TypedQuery.class);
		
		when(mockUser.getUsername()).thenReturn(null);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);	
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT u FROM User u WHERE u.username = '" + nonExistingUsername + "'", User.class)).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(null);
		
		String result = ud.verifyCredentials(nonExistingUsername, password);
		assertEquals("Username was not found!", result);
	}
	
	@Test
	public void passenger_is_added_to_db() {
		String username = "Karthik";
		String query = "SELECT u FROM User u WHERE u.username = '" + username + "'";
		Passenger mockP = mock(Passenger.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery mockQuery = mock(TypedQuery.class);
		User mockUser = mock(User.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery(query)).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(mockUser);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQuery, mockUser);
		
		UserDao ud = new UserDao(mockEmf);
		ud.addPassenger(username, mockP);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).createQuery(query);
		order.verify(mockQuery).getSingleResult();
		order.verify(mockUser).addPassenger(mockP);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_username_passengers_set_returned() {
		String username = "Karthik";
		String query = "SELECT u FROM User u WHERE u.username = '" + username + "'";
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		TypedQuery mockQuery = mock(TypedQuery.class);
		User mockUser = mock(User.class);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery(query)).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(mockUser);
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockQuery, mockUser);
		
		UserDao ud = new UserDao(mockEmf);
		ud.getPassengers(username);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).createQuery(query);
		order.verify(mockQuery).getSingleResult();
		order.verify(mockUser).getPassengers();
		order.verify(mockEm).close();
	}
}
