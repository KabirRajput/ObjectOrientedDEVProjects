package jpaDemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_table")
public class User {

	@Id
	private int id; // jpa requies a primary key
	private String name;
	private String password;
	private double heightInMeters;

	public User(int id, String name, String password, double heightInMeters) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.heightInMeters = heightInMeters;
	}

	public User() {
		// required by JPA
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getHeightInMeters() {
		return heightInMeters;
	}

	public void setHeightInMeters(double heightInMeters) {
		this.heightInMeters = heightInMeters;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", heightInMeters=" + heightInMeters
				+ "]";
	}

	
}
