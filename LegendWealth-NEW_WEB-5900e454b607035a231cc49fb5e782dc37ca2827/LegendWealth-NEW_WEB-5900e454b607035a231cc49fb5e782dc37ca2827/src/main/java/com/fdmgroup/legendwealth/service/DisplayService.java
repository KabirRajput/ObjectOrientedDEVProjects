package com.fdmgroup.legendwealth.service;

import java.util.*;

import javax.servlet.ServletContext;

import com.fdmgroup.legendwealth.dal.GenericDao;


public class DisplayService<T extends GenericDao, X> {
	private T genericDao; 

	
	public DisplayService(T genericDao) {
		this.setGenericDao(genericDao);
	}

	public List<X> getList() {
		List<X> genericList = getGenericDao().getList();
		return genericList;
	}

	public T getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(T genericDao) {
		this.genericDao = genericDao;
	}

}
