package jpaDemo.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.runners.Parameterized.UseParametersRunnerFactory;

import jpaDemo.entity.User;

public class UserDao {

	private EntityManagerFactory entityManagerFactory;

	public UserDao(EntityManagerFactory entityManagerFactory) {
		super();
		this.entityManagerFactory = entityManagerFactory;
	}

	public void add(User user) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(user);
		//everything between persist and commit marks the transaction
		entityTransaction.commit();
		entityManager.close();
	}

	public User getByPrimaryKey(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		User returnedUser = entityManager.find(User.class,id);
		//everything between persist and commit marks the transaction
		entityTransaction.commit();
		entityManager.close();
		return returnedUser;				
	}

}
