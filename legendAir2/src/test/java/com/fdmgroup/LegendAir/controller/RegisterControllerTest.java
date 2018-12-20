package com.fdmgroup.LegendAir.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.LegendAir.service.RegisterService;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

public class RegisterControllerTest {

	@Test
	public void dispatch_register_jsp_when_get_request_on_register() throws ServletException, IOException {
		//arrange
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		RequestDispatcher mockRd = mock(RequestDispatcher.class);
		when(mockReq.getRequestDispatcher("WEB-INF/views/register.jsp")).thenReturn(mockRd);
		InOrder order = inOrder(mockReq, mockRd);
		RegisterController rc = new RegisterController();
		
		//act
		rc.doGet(mockReq, mockRes);
		
		//assert
		order.verify(mockReq).getRequestDispatcher("WEB-INF/views/register.jsp");
		order.verify(mockRd).forward(mockReq, mockRes);
	}
	
	@Test
	public void test_valid_username_and_password_on_post_request() throws ServletException, IOException {
		String username = "a username";
		String password = "pass";
		String confirmPassword = "pass";
		String result = "Registration successful";
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		ServletContext mockSc = mock(ServletContext.class);
		RegisterService mockRs = mock(RegisterService.class);
		
		when(mockReq.getParameter("username")).thenReturn(username);
		when(mockReq.getParameter("password")).thenReturn(password);
		when(mockReq.getParameter("confirmPassword")).thenReturn(confirmPassword);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockSc);
		when(mockSc.getAttribute("registerService")).thenReturn(mockRs);
		when(mockRs.register(username, password, confirmPassword)).thenReturn(result);
		InOrder order = inOrder(mockReq, mockRes, mockSession, mockSc, mockRs);
		
		RegisterController rc = new RegisterController();
		rc.doPost(mockReq, mockRes);
		
		order.verify(mockReq).getParameter("username");
		order.verify(mockReq).getParameter("password");
		order.verify(mockReq).getParameter("confirmPassword");
		order.verify(mockReq).getSession();
		order.verify(mockSession).getServletContext();
		order.verify(mockSc).getAttribute("registerService");
		order.verify(mockRs).register(username, password, confirmPassword);
		order.verify(mockSession).setAttribute("active_username", username);
		order.verify(mockRes).sendRedirect(mockReq.getContextPath() + "/");		
	}
	
	@Test
	public void test_valid_username_but_not_matching_passwords() throws ServletException, IOException {
		String username = "a username";
		String password = "pass";
		String confirmPassword = "wrong pass";
		String result = "Passwords do not match";
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		ServletContext mockSc = mock(ServletContext.class);
		RegisterService mockRs = mock(RegisterService.class);
		RequestDispatcher mockRd = mock(RequestDispatcher.class);
		
		when(mockReq.getParameter("username")).thenReturn(username);
		when(mockReq.getParameter("password")).thenReturn(password);
		when(mockReq.getParameter("confirmPassword")).thenReturn(confirmPassword);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockSc);
		when(mockSc.getAttribute("registerService")).thenReturn(mockRs);
		when(mockRs.register(username, password, confirmPassword)).thenReturn(result);
		when(mockReq.getRequestDispatcher("WEB-INF/views/register.jsp")).thenReturn(mockRd);
		InOrder order = inOrder(mockReq, mockRes, mockSession, mockSc, mockRs, mockRd);
		
		RegisterController rc = new RegisterController();
		rc.doPost(mockReq, mockRes);
		
		order.verify(mockReq).getParameter("username");
		order.verify(mockReq).getParameter("password");
		order.verify(mockReq).getParameter("confirmPassword");
		order.verify(mockReq).getSession();
		order.verify(mockSession).getServletContext();
		order.verify(mockSc).getAttribute("registerService");
		order.verify(mockRs).register(username, password, confirmPassword);
		order.verify(mockReq).setAttribute("password_error", result);
		order.verify(mockReq).setAttribute("username_error", null);
		order.verify(mockRd).forward(mockReq, mockRes);
	}
	
	@Test
	public void test_invalid_username_but_not_matching_passwords() throws ServletException, IOException {
		String username = "a username";
		String password = "pass";
		String confirmPassword = "pass";
		String result = "Username already exists";
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		HttpSession mockSession = mock(HttpSession.class);
		ServletContext mockSc = mock(ServletContext.class);
		RegisterService mockRs = mock(RegisterService.class);
		RequestDispatcher mockRd = mock(RequestDispatcher.class);
		
		when(mockReq.getParameter("username")).thenReturn(username);
		when(mockReq.getParameter("password")).thenReturn(password);
		when(mockReq.getParameter("confirmPassword")).thenReturn(confirmPassword);
		when(mockReq.getSession()).thenReturn(mockSession);
		when(mockSession.getServletContext()).thenReturn(mockSc);
		when(mockSc.getAttribute("registerService")).thenReturn(mockRs);
		when(mockRs.register(username, password, confirmPassword)).thenReturn(result);
		when(mockReq.getRequestDispatcher("WEB-INF/views/register.jsp")).thenReturn(mockRd);
		InOrder order = inOrder(mockReq, mockRes, mockSession, mockSc, mockRs, mockRd);
		
		RegisterController rc = new RegisterController();
		rc.doPost(mockReq, mockRes);
		
		order.verify(mockReq).getParameter("username");
		order.verify(mockReq).getParameter("password");
		order.verify(mockReq).getParameter("confirmPassword");
		order.verify(mockReq).getSession();
		order.verify(mockSession).getServletContext();
		order.verify(mockSc).getAttribute("registerService");
		order.verify(mockRs).register(username, password, confirmPassword);
		order.verify(mockReq).setAttribute("username_error", result);
		order.verify(mockReq).setAttribute("password_error", null);
		order.verify(mockRd).forward(mockReq, mockRes);
	}
}
