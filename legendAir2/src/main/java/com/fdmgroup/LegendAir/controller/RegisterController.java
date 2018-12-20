package com.fdmgroup.LegendAir.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.LegendAir.dal.UserDao;
import com.fdmgroup.LegendAir.entity.User;
import com.fdmgroup.LegendAir.factory.RegisterServiceFactory;
import com.fdmgroup.LegendAir.service.RegisterService;

public class RegisterController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/register.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		HttpSession session = req.getSession();
		ServletContext servletContext = session.getServletContext();
		RegisterService registerService = (RegisterService) servletContext.getAttribute("registerService");
		String result = registerService.register(username, password, confirmPassword);
		
		if(result.equals("Registration successful")) {
			session.setAttribute("active_username", username);
			resp.sendRedirect(req.getContextPath() +"/");
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/register.jsp");
			if(result.equals("Passwords do not match")) {
				req.setAttribute("password_error", result);
				req.setAttribute("username_error", null);				
			} else {
				req.setAttribute("username_error", result);
				req.setAttribute("password_error", null);			
			}
			rd.forward(req, resp);
		} 
	}
}
