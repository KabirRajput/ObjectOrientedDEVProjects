package com.fdmgroup.legendwealth.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		HttpSession session = req.getSession();
		if (session.getAttribute("active_broker") != null) {
			rd = req.getRequestDispatcher("home");
		}
		else {
			rd = req.getRequestDispatcher("WEB-INF/views/index.jsp");
		}
		rd.forward(req, resp);			
	}

	
}
