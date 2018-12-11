package com.fdm.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		//if you wanted to, now you can edit or add data to index.jsp
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/login.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("form_firstname");
		String password = req.getParameter("form_password");
		// excellent place to have a service
		// e.g. login service which would use the userDao
		HttpSession session = req.getSession();
		ServletContext servletContext = session.getServletContext();
		String ud = (String) servletContext.getAttribute("userDao");
		System.out.println("I got object = " + ud);

		RequestDispatcher rd;
		if(username.toLowerCase().contains("kabir") || password.equals("password")) {
			rd = req.getRequestDispatcher("WEB-INF/views/blocked.jsp");
		} else {
			rd = req.getRequestDispatcher("WEB-INF/views/home.jsp");

			session.setAttribute("active_username", username);			
			req.setAttribute("lucky_numbers", new int[] {1,2,3,4,5,6,7,8,9});
		}
		rd.forward(req, resp);
	}


}
