package com.fdmgroup.legendwealth.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.legendwealth.dal.AssetDao;
import com.fdmgroup.legendwealth.dal.PortfolioDao;
import com.fdmgroup.legendwealth.entity.Asset;
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.service.PortfolioService;
import com.google.gson.JsonElement;

public class TradeDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		HttpSession session = req.getSession();
		
		AssetDao assetDao = (AssetDao) session.getServletContext().getAttribute("assetDao");
		Asset asset = assetDao.getById(Long.parseLong(req.getParameter("asset_id")));
		
		PortfolioDao portfolioDao = (PortfolioDao) session.getServletContext().getAttribute("portfolioDao");
		Portfolio portfolio = portfolioDao.getById(Long.parseLong(req.getParameter("portfolio_id")));
		
		PortfolioService portfolioSummarySerivce = (PortfolioService) session.getServletContext().getAttribute("portfolioSummarySerivce");
		
		JsonElement portfolioAssetSummary = portfolioSummarySerivce.getPortfolioAssetSummary(portfolio);		
		
		
		req.setAttribute("portfolioAssetSummary", portfolioAssetSummary);
		session.setAttribute("asset", asset);
		rd = req.getRequestDispatcher("WEB-INF/views/trade-detail.jsp");
		rd.forward(req, resp);
	}

}
