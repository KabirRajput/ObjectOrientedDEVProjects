package jpaDemo.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.mockito.InOrder;

import jpaDemo.entity.User;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserDaoTest {

	@Test
	public void given_user_whenAddingUserToDB_then_userIsPersistedAndResourcesClosed() {
		
		User mockUser = mock(User.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		InOrder order = inOrder(mockEmf,mockEm,mockEt);
				
		UserDao userDao = new UserDao(mockEmf);
		userDao.add(mockUser);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).persist(mockUser);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
	}
	
	@Test
	public void given_existingUserId_when_findingById_then_returns_user_from_DB() {
		int id = 999;
		
		User mockUser = mock(User.class);
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(User.class, id)).thenReturn(mockUser);
		
		UserDao userDao = new UserDao(mockEmf);
		userDao.getByPrimaryKey(id);
		
		InOrder order = inOrder(mockEmf,mockEm,mockEt);
		
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		User returnedUser = order.verify(mockEm).find(User.class,id);  //only look at columns in table User
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		
		assertEquals(mockUser, returnedUser);
	}
		

	
}
