package com.fdmgroup.legendwealth.filter;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.legendwealth.entity.Broker;
import com.fdmgroup.legendwealth.filter.VerifyLoginFilter;

public class VerifyLoginFilterTest {

	@Test
	public void if_user_not_login_then_forward_to_root() throws ServletException, IOException {
		
		ServletResponse mockResponse = mock(ServletResponse.class);
		FilterChain mockFilterChain = mock(FilterChain.class);
		HttpServletRequest mockHttpRequest = mock(HttpServletRequest.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);
		
		when(mockHttpRequest.getSession()).thenReturn(mockSession);
		when(mockHttpRequest.getRequestDispatcher("")).thenReturn(mockRD);
		
		VerifyLoginFilter verifyLoginFilter = new VerifyLoginFilter();
		verifyLoginFilter.doFilter(mockHttpRequest, mockResponse, mockFilterChain);
		
		InOrder order = inOrder(mockHttpRequest, mockRD);
		
		order.verify(mockHttpRequest).getSession();
		order.verify(mockHttpRequest).getRequestDispatcher("");
		order.verify(mockRD).forward(mockHttpRequest, mockResponse);

	}
	
	@Test
	public void if_user_has_login_then_chain_to_next_filter() throws ServletException, IOException {
		
		ServletResponse mockResponse = mock(ServletResponse.class);
		FilterChain mockFilterChain = mock(FilterChain.class);
		HttpServletRequest mockHttpRequest = mock(HttpServletRequest.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);
		Broker mockBroker = mock(Broker.class);
		
		when(mockHttpRequest.getSession()).thenReturn(mockSession);
		when(mockHttpRequest.getRequestDispatcher("")).thenReturn(mockRD);
		when(mockSession.getAttribute("active_broker")).thenReturn(mockBroker);
		
		VerifyLoginFilter verifyLoginFilter = new VerifyLoginFilter();
		verifyLoginFilter.doFilter(mockHttpRequest, mockResponse, mockFilterChain);
		
		InOrder order = inOrder(mockHttpRequest, mockFilterChain);
		
		order.verify(mockHttpRequest).getSession();
		order.verify(mockFilterChain).doFilter(mockHttpRequest, mockResponse);

	}
}
