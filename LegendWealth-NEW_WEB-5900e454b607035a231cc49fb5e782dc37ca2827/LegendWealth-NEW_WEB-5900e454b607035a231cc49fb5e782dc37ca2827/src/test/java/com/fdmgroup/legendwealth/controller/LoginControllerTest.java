package com.fdmgroup.legendwealth.controller;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.legendwealth.dal.BrokerDao;
import com.fdmgroup.legendwealth.entity.Broker;
import com.fdmgroup.legendwealth.service.LoginService;

public class LoginControllerTest {
	@Test
	public void when_login_is_requested_by_POST_with_correct_credientials_then_forward_to_home() throws ServletException, IOException {
		
		String username = "harry.porter";
		String password = "harry.porter123";

		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);
		ServletContext mockServletContext = mock(ServletContext.class);
		
		BrokerDao mockBrokerDao = mock(BrokerDao.class);
		Broker mockBroker = mock(Broker.class);
		

		when(mockRequest.getParameter("login_form_username")).thenReturn(username);
		when(mockRequest.getParameter("login_form_password")).thenReturn(password);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockServletContext);
		when(mockServletContext.getAttribute("brokerDao")).thenReturn(mockBrokerDao);
		when(mockBrokerDao.getBrokerByUsernameAndPassword(username, password)).thenReturn(mockBroker);
		when(mockRequest.getRequestDispatcher("home")).thenReturn(mockRD);
		
		LoginController loginController = new LoginController();
		loginController.doPost(mockRequest, mockResponse);
		
		InOrder order = inOrder(mockRequest, mockSession, mockRD);
		order.verify(mockRequest).getParameter("login_form_username");
		order.verify(mockRequest).getParameter("login_form_password");
		order.verify(mockRequest).getSession();
		order.verify(mockSession).getServletContext();
		order.verify(mockRequest).getRequestDispatcher("home");
		order.verify(mockRD).forward(mockRequest, mockResponse);
	}
	
	@Test
	public void when_login_is_requested_by_POST_with_incorrect_credientials_then_forward_to_home() throws ServletException, IOException {
		
		String username = "harry.porter";
		String password = "harry.porter123";

		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);
		ServletContext mockServletContext = mock(ServletContext.class);
		
		BrokerDao mockBrokerDao = mock(BrokerDao.class);
		Broker mockBroker = mock(Broker.class);
		

		when(mockRequest.getParameter("login_form_username")).thenReturn(username);
		when(mockRequest.getParameter("login_form_password")).thenReturn(password);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockServletContext);
		when(mockServletContext.getAttribute("brokerDao")).thenReturn(mockBrokerDao);
		when(mockBrokerDao.getBrokerByUsernameAndPassword(username, password)).thenReturn(null);
		when(mockRequest.getRequestDispatcher("WEB-INF/views/index.jsp")).thenReturn(mockRD);

		
		LoginController loginController = new LoginController();
		loginController.doPost(mockRequest, mockResponse);
		
		InOrder order = inOrder(mockRequest, mockRD);
		order.verify(mockRequest).getSession();
		order.verify(mockRD).forward(mockRequest, mockResponse);
	}
	
	@Test
	public void when_login_is_requested_by_GET_if_user_has_login_foward_to_home() throws ServletException, IOException {
		
		Broker mockBroker = mock(Broker.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRD = mock(RequestDispatcher.class);
		
		
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("active_broker")).thenReturn(mockBroker);
		when(mockRequest.getRequestDispatcher("home")).thenReturn(mockRD);

		LoginController loginController = new LoginController();
		loginController.doGet(mockRequest, mockResponse);
		
		InOrder order = inOrder(mockRequest, mockRD);
		order.verify(mockRequest).getSession();
		order.verify(mockRD).forward(mockRequest, mockResponse);
	}
}
