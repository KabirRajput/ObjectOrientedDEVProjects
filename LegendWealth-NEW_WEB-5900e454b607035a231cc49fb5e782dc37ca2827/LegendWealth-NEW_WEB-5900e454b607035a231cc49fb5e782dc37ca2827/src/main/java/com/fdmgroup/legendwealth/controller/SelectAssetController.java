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
import com.fdmgroup.legendwealth.entity.Asset;
import com.fdmgroup.legendwealth.entity.Portfolio;
import com.fdmgroup.legendwealth.service.PortfolioService;

public class SelectAssetController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		HttpSession session = req.getSession();
		long id = Long.parseLong(req.getParameter("id"));
		PortfolioService portfolioSummarySerivce = (PortfolioService) session.getServletContext().getAttribute("portfolioSummarySerivce");

		
		Portfolio p = portfolioSummarySerivce.getPortfolioById(id);
		
		AssetDao assetDao = (AssetDao) session.getServletContext().getAttribute("assetDao");
		List<Asset> assetList = assetDao.getList();
		
		req.setAttribute("assetList", assetList);
		rd = req.getRequestDispatcher("WEB-INF/views/select-asset.jsp");
		rd.forward(req, resp);
	}

}
