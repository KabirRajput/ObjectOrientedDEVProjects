package com.fdm.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockedController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		//if you wanted to, now you can edit or add data to index.jsp
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/blocked.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		//if you wanted to, now you can edit or add data to index.jsp
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/blocked.jsp");
		rd.forward(req, resp);
		
	}
}
