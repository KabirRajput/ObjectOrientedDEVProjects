package com.fdm.listner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationContextListner implements ServletContextListener {

	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Servlet Context Initialized");
		String userDao = new String("UserDao Instance");
		String bookDao = new String("bookDao Instance");
		String orderDao = new String("OrderDao Instance");
		String categoryDao = new String("CategoryDao Instance");
		
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("userDao", userDao);
		servletContext.setAttribute("bookDao", bookDao);
		servletContext.setAttribute("orderDao", orderDao);
		servletContext.setAttribute("categoryDao", categoryDao);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("servlet Context destroyed");
	}
}
