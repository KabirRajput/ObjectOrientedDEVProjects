package com.fdmgroup.legendwealth.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.legendwealth.dal.TradeDao;

public class HomeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		HttpSession session = req.getSession();
		
		TradeDao tradeDao = (TradeDao) session.getServletContext().getAttribute("tradeDao");
		List<Object[]> portfolioList = tradeDao.getPortfolioList();
		List<Object[]> cashInPortfolioList = tradeDao.getCashinPortfolio();
	
		req.setAttribute("portfolioList", portfolioList);
		req.setAttribute("cashInPortfolioList", cashInPortfolioList);
		rd = req.getRequestDispatcher("WEB-INF/views/home.jsp");

		rd.forward(req, resp);
	}


}
