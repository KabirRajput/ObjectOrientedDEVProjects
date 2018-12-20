package com.kabir.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kabir.model.Customer;

@Repository("customerRepository")
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
