package com.fdmgroup.LegendAir.controller;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.LegendAir.dal.UserDao;
import com.fdmgroup.LegendAir.service.LoginService;

public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("active_username") != null) {
			resp.sendRedirect(req.getContextPath() + "/");
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/login.jsp");
			
			rd.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		ServletContext servletContext = session.getServletContext();
		LoginService loginService = (LoginService) servletContext.getAttribute("loginService");
		String result = loginService.login(username, password);	
		
		if(result.equals("true")) {
			session.setAttribute("active_username", username);
			resp.sendRedirect(req.getContextPath() + "/");
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/login.jsp");
			if(result.equals("Username was not found!")) {
				req.setAttribute("username_error", result);
			} else {
				req.setAttribute("password_error", result);
			}
			rd.forward(req, resp);
		}
	}
}
