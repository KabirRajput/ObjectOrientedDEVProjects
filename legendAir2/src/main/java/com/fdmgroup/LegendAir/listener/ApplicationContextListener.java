package com.fdmgroup.LegendAir.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fdmgroup.LegendAir.dal.AirportDao;
import com.fdmgroup.LegendAir.dal.BookingDao;
import com.fdmgroup.LegendAir.dal.FlightDao;
import com.fdmgroup.LegendAir.dal.PassengerDao;
import com.fdmgroup.LegendAir.dal.PaymentMethodDao;
import com.fdmgroup.LegendAir.dal.UserDao;
import com.fdmgroup.LegendAir.factory.PassengerFactory;
import com.fdmgroup.LegendAir.service.AirportService;
import com.fdmgroup.LegendAir.service.FlightService;
import com.fdmgroup.LegendAir.service.LoginService;
import com.fdmgroup.LegendAir.service.PassengerService;
import com.fdmgroup.LegendAir.service.RegisterService;
import com.fdmgroup.LegendAir.wrapper.GsonWrapper;
import com.google.gson.Gson;

public class ApplicationContextListener  implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_legendAir");
		GsonWrapper gson = new GsonWrapper();
		AirportDao ad = new AirportDao(emf);
		BookingDao bd = new BookingDao(emf);
		FlightDao fd = new FlightDao(emf);
		PassengerDao pd = new PassengerDao(emf);
		PaymentMethodDao pmd = new PaymentMethodDao(emf);
		UserDao ud = new UserDao(emf);
		LoginService loginService = new LoginService(ud);
		RegisterService registerService = new RegisterService(ud);
		AirportService airportService = new AirportService(ad, gson);
		FlightService flightService = new FlightService(fd);
		PassengerFactory pf = new PassengerFactory();
		PassengerService passengerService = new PassengerService(ud, pf);
		ServletContext sc = sce.getServletContext();
		sc.setAttribute("ad", ad);
		sc.setAttribute("bd", bd);
		sc.setAttribute("fd", fd);
		sc.setAttribute("pd", pd);
		sc.setAttribute("pmd", pmd);
		sc.setAttribute("ud", ud);
		sc.setAttribute("loginService", loginService);
		sc.setAttribute("registerService", registerService);
		sc.setAttribute("airportService", airportService);
		sc.setAttribute("flightService", flightService);
		sc.setAttribute("passengerService", passengerService);
	}

}
