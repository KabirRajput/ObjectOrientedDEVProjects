package com.fdmgroup.legendwealth.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.legendwealth.dal.PortfolioDao;
import com.fdmgroup.legendwealth.dal.TradeDao;
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.service.PortfolioService;
import com.google.gson.JsonElement;

public class PortfolioController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		HttpSession session = req.getSession();
		long id = Long.parseLong(req.getParameter("id"));
		
		PortfolioService portfolioSummarySerivce = (PortfolioService) session.getServletContext().getAttribute("portfolioSummarySerivce");
		
		Portfolio portfolio = portfolioSummarySerivce.getPortfolioById(id);
		JsonElement portfolioAssetSummary = portfolioSummarySerivce.getPortfolioAssetSummary(portfolio);		
		
		
		req.setAttribute("portfolioAssetSummary", portfolioAssetSummary);
		req.setAttribute("portfolio_id", id);
		
		session.setAttribute("portfolio", portfolio);
		
		rd = req.getRequestDispatcher("WEB-INF/views/portfolio.jsp");
		rd.forward(req, resp);
	}
	
}
