package com.fdmgroup.legendwealth.service;

import org.junit.Test;

import com.fdmgroup.legendwealth.dal.GenericDao;
import com.fdmgroup.legendwealth.dal.PortfolioDao;
import com.fdmgroup.legendwealth.entity.Portfolio;

import static org.mockito.Mockito.*;

public class DisplayServiceTest {
	
	@Test
	public void when_getList_method_is_called_it_invokes_getList_method_of_genericDao() {
		
		PortfolioDao mockPortfolioDao = mock(PortfolioDao.class);
		DisplayService<PortfolioDao, Portfolio> displayService = new DisplayService<PortfolioDao, Portfolio>(mockPortfolioDao);
	
		displayService.getList();
		
		verify(displayService.getGenericDao()).getList();
	
	}

}
