package com.fdmgroup.LegendAir.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.dal.UserDao;
import com.fdmgroup.LegendAir.service.LoginService;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

public class LoginControllerTest {

	@Test
	public void forward_to_login_page_when_get_request() throws ServletException, IOException {
		//arrange
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		RequestDispatcher mockRd = mock(RequestDispatcher.class);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockReq.getRequestDispatcher("WEB-INF/views/login.jsp")).thenReturn(mockRd);
		InOrder order = inOrder(mockReq, mockSession, mockRd);
		
		//act
		LoginController lc = new LoginController();
		lc.doGet(mockReq, mockRes);
		
		//assert
		order.verify(mockReq).getSession();
		order.verify(mockSession).getAttribute("active_username");
		order.verify(mockReq).getRequestDispatcher("WEB-INF/views/login.jsp");
		order.verify(mockRd).forward(mockReq, mockRes);
	}
	
	@Test
	public void forward_to_index_page_when_get_request_and_user_already_logged_in() throws ServletException, IOException {
		//arrange
		String username = "a username";
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("active_username")).thenReturn(username);
		InOrder order = inOrder(mockReq, mockSession, mockRes);
		
		//act
		LoginController lc = new LoginController();
		lc.doGet(mockReq, mockRes);
		
		//assert
		order.verify(mockReq).getSession();
		order.verify(mockSession).getAttribute("active_username");
		order.verify(mockRes).sendRedirect(mockReq.getContextPath() + "/");
	}
	
	@Test
	public void credentials_are_correct_user_is_forwarded_to_index() throws ServletException, IOException {
		//arrange
		String username = "user";
		String password = "pass";
		String result = "true";
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		ServletContext mockSc = mock(ServletContext.class);
		LoginService mockLs = mock(LoginService.class);
		when(mockReq.getParameter("username")).thenReturn(username);
		when(mockReq.getParameter("password")).thenReturn(password);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockSc);
		when(mockSc.getAttribute("loginService")).thenReturn(mockLs);
		when(mockLs.login(username, password)).thenReturn(result);
		InOrder order = inOrder(mockSession, mockReq, mockSc, mockLs, mockRes);
		
		
		//act
		LoginController lc = new LoginController();
		lc.doPost(mockReq, mockRes);
		
		//arrange
		order.verify(mockReq).getParameter("username");
		order.verify(mockReq).getParameter("password");
		order.verify(mockReq).getSession();
		order.verify(mockSession).getServletContext();
		order.verify(mockSc).getAttribute("loginService");
		order.verify(mockLs).login(username, password);
		order.verify(mockSession).setAttribute("active_username", username);
		order.verify(mockRes).sendRedirect(mockReq.getContextPath() + "/");
	}
	
	@Test
	public void username_is_not_found_is_forwarded_to_login_with_error() throws ServletException, IOException {
		//arrange
		String username = "user";
		String password = "pass";
		String result = "Username was not found!";
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		ServletContext mockSc = mock(ServletContext.class);
		LoginService mockLs = mock(LoginService.class);
		RequestDispatcher mockRd = mock(RequestDispatcher.class);
		when(mockReq.getParameter("username")).thenReturn(username);
		when(mockReq.getParameter("password")).thenReturn(password);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockSc);
		when(mockSc.getAttribute("loginService")).thenReturn(mockLs);
		when(mockReq.getRequestDispatcher("WEB-INF/views/login.jsp")).thenReturn(mockRd);
		when(mockLs.login(username, password)).thenReturn(result);
		InOrder order = inOrder(mockSession, mockReq, mockRd, mockSc, mockLs);
		
		
		//act
		LoginController lc = new LoginController();
		lc.doPost(mockReq, mockRes);
		
		//arrange
		order.verify(mockReq).getParameter("username");
		order.verify(mockReq).getParameter("password");
		order.verify(mockReq).getSession();
		order.verify(mockSession).getServletContext();
		order.verify(mockSc).getAttribute("loginService");
		order.verify(mockLs).login(username, password);
		order.verify(mockReq).getRequestDispatcher("WEB-INF/views/login.jsp");
		order.verify(mockReq).setAttribute("username_error", result);
		mockRd.forward(mockReq, mockRes);
	}
	
	@Test
	public void username_is_found_but_password_not_correct_is_forwarded_to_login_with_error() throws ServletException, IOException {
		//arrange
		String username = "user";
		String password = "pass";
		String result = "Password is not valid!";
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		ServletContext mockSc = mock(ServletContext.class);
		LoginService mockLs = mock(LoginService.class);
		RequestDispatcher mockRd = mock(RequestDispatcher.class);
		when(mockReq.getParameter("username")).thenReturn(username);
		when(mockReq.getParameter("password")).thenReturn(password);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockSc);
		when(mockSc.getAttribute("loginService")).thenReturn(mockLs);
		when(mockReq.getRequestDispatcher("WEB-INF/views/login.jsp")).thenReturn(mockRd);
		when(mockLs.login(username, password)).thenReturn(result);
		InOrder order = inOrder(mockSession, mockReq, mockRd, mockSc, mockLs);
		
		
		//act
		LoginController lc = new LoginController();
		lc.doPost(mockReq, mockRes);
		
		//arrange
		order.verify(mockReq).getParameter("username");
		order.verify(mockReq).getParameter("password");
		order.verify(mockReq).getSession();
		order.verify(mockSession).getServletContext();
		order.verify(mockSc).getAttribute("loginService");
		order.verify(mockLs).login(username, password);
		order.verify(mockReq).getRequestDispatcher("WEB-INF/views/login.jsp");
		order.verify(mockReq).setAttribute("password_error", result);
		mockRd.forward(mockReq, mockRes);
	}
}
