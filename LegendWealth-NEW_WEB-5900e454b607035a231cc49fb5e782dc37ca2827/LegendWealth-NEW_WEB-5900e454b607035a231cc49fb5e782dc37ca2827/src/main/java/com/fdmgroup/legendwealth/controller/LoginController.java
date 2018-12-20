package com.fdmgroup.legendwealth.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.legendwealth.dal.BrokerDao;
import com.fdmgroup.legendwealth.entity.Broker;

public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		if (session.getAttribute("active_broker") != null) {
			req.getRequestDispatcher("home").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("login_form_username");
		String password = req.getParameter("login_form_password");
		RequestDispatcher rd;

		// Login service which would use the brokerDao
		HttpSession session = req.getSession();
		
		BrokerDao brokerDao = (BrokerDao) session.getServletContext().getAttribute("brokerDao");
		

		Broker brokerToRetrieve = brokerDao.getBrokerByUsernameAndPassword(username, password);
		
		if (brokerToRetrieve != null) {
			rd = req.getRequestDispatcher("home");
			session.setAttribute("active_firstname", brokerToRetrieve.getFirstName());
			session.setAttribute("active_broker", brokerToRetrieve);
		} else {
			req.setAttribute("login_fail", true);
			rd = req.getRequestDispatcher("WEB-INF/views/index.jsp");
		}

		rd.forward(req, resp);

	}

}
