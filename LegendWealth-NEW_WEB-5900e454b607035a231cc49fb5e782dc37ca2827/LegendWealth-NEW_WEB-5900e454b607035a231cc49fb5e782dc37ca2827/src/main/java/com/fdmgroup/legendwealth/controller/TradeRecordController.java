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
import com.fdmgroup.legendwealth.entity.Trade;
import com.fdmgroup.legendwealth.service.DisplayService;

public class TradeRecordController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		HttpSession session = req.getSession();
		
		TradeDao tradeDao = (TradeDao) session.getServletContext().getAttribute("tradeDao");
		List<Trade> tradeList = tradeDao.getList();
		
		session.setAttribute("tradeList", tradeList);
		
		rd = req.getRequestDispatcher("WEB-INF/views/trade-record.jsp");

		rd.forward(req, resp);
	}
}
