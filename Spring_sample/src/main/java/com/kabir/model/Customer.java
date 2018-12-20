package com.kabir.model;

public class Customer {
	private String firstname;
	private String lastname;

	
	
	public Customer() {
		//required by Spring
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
