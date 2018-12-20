package com.kabir.repository;

import java.util.List;

import com.kabir.model.Customer;

public interface CustomerRepository {

	List<Customer> findAll();

}