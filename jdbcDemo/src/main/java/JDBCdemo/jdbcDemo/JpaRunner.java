package JDBCdemo.jdbcDemo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jpaDemo.dal.UserDao;
import jpaDemo.entity.User;

public class JpaRunner {

	public static void main(String args[]) {

		User user = new User(4, "Mic", "pass", 1.830);

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_demo");
		UserDao userDao = new UserDao(entityManagerFactory);

		userDao.add(user);

		User user2 = userDao.getByPrimaryKey(2);
		System.out.println(user2);

		entityManagerFactory.close();

	}
}
