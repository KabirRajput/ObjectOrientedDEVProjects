package com.fdmgroup.legendwealth.filter;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.Test;
import org.mockito.InOrder;

public class InjectionAttackFilterTest {
	@Test
	public void if_username_filled_with_open_ankle_bracket_then_forward_to_blocked() throws ServletException, IOException {

		String username = "<";
		InjectionAttackFilter injectionAttackFilter = new InjectionAttackFilter();
		ServletRequest mockRequest = mock(ServletRequest.class);
		ServletResponse mockResponse = mock(ServletResponse.class);
		FilterChain mockFilterChain = mock(FilterChain.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);

		when(mockRequest.getParameter("login_form_username")).thenReturn(username);
		when(mockRequest.getRequestDispatcher("/blocked")).thenReturn(mockRD);
		
		injectionAttackFilter.doFilter(mockRequest, mockResponse, mockFilterChain);

		InOrder order = inOrder(mockRequest, mockRD);

		order.verify(mockRequest).getParameter("login_form_username");
		order.verify(mockRequest).getRequestDispatcher("/blocked");
		order.verify(mockRD).forward(mockRequest, mockResponse);
	}
	
	@Test
	public void if_username_doesnot_contain_open_ankle_bracket_then_chain_to_next_filter() throws ServletException, IOException {

		String username = "Happy";
		InjectionAttackFilter injectionAttackFilter = new InjectionAttackFilter();
		ServletRequest mockRequest = mock(ServletRequest.class);
		ServletResponse mockResponse = mock(ServletResponse.class);
		FilterChain mockFilterChain = mock(FilterChain.class);

		when(mockRequest.getParameter("login_form_username")).thenReturn(username);
		
		injectionAttackFilter.doFilter(mockRequest, mockResponse, mockFilterChain);

		InOrder order = inOrder(mockRequest, mockFilterChain);

		order.verify(mockRequest).getParameter("login_form_username");
		order.verify(mockFilterChain).doFilter(mockRequest, mockResponse);
	}

}
