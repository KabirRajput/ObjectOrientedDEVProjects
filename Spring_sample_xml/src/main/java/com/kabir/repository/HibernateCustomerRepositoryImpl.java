package com.kabir.repository;

import java.util.ArrayList;
import java.util.List;

import com.kabir.model.Customer;

public class HibernateCustomerRepositoryImpl implements CustomerRepository {

	/* (non-Javadoc)
	 * @see com.kabir.repository.CustomerRepository#findAll()
	 */
	@Override
	public List<Customer> findAll() {

		List<Customer> 	customers = new ArrayList<>();
		
		Customer customer = new Customer();
		customer.setFirstname("Brian");
		customer.setLastname("Hines");
		customers.add(customer);
		
		return customers;
	} 

}
