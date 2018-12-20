package com.fdmgroup.legendwealth.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.legendwealth.dal.TradeDao;
import com.fdmgroup.legendwealth.entity.Asset;
import com.fdmgroup.legendwealth.entity.Broker;
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.service.PortfolioService;
import com.fdmgroup.legendwealth.service.TradeService;
import com.google.gson.JsonElement;


public class TradeOperationController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Broker broker = (Broker) session.getAttribute("active_broker");
		Portfolio portfolio = (Portfolio) session.getAttribute("portfolio");
		Asset asset = (Asset) session.getAttribute("asset");
		Double quantity = Double.parseDouble(req.getParameter("trade_form_quantityTotal"));
		Double priceTotal = Double.parseDouble(req.getParameter("trade_form_priceTotal"));
		RequestDispatcher rd;
		
		TradeService tradeService = (TradeService) session.getServletContext().getAttribute("tradeService");
		
		String message = tradeService.changeAsset(broker, portfolio, asset, quantity, priceTotal);
		
		PortfolioService portfolioSummarySerivce = (PortfolioService) session.getServletContext().getAttribute("portfolioSummarySerivce");
		JsonElement portfolioAssetSummary = portfolioSummarySerivce.getPortfolioAssetSummary(portfolio);		
		
		req.setAttribute("portfolioAssetSummary", portfolioAssetSummary);
		req.setAttribute("success_message", message);
		
		rd = req.getRequestDispatcher("WEB-INF/views/portfolio.jsp");
		
		rd.forward(req, resp);
	}

}
