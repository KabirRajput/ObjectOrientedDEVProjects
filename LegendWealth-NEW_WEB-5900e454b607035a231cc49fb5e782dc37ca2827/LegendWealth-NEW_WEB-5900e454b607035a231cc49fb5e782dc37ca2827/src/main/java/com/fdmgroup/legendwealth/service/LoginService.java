package com.fdmgroup.legendwealth.service;


import com.fdmgroup.legendwealth.dal.BrokerDao;
import com.fdmgroup.legendwealth.entity.Broker;

public class LoginService {
	private BrokerDao brokerDao;

	public LoginService(BrokerDao brokerDao) {
		this.brokerDao = brokerDao;
	}

	public Broker login(String username, String password) {
		Broker brokerToRetrieve = brokerDao.getBrokerByUsernameAndPassword(username, password);
		return brokerToRetrieve;
	}

}
