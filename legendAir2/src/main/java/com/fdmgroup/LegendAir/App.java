package com.fdmgroup.LegendAir;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;
import javax.swing.border.EtchedBorder;

import com.fdmgroup.LegendAir.dal.*;
import com.fdmgroup.LegendAir.entity.*;
import com.google.gson.Gson;


public class App {
	public static void main(String[] args) throws IOException, ParseException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_legendAir");
		AirportDao ad = new AirportDao(emf);
		FlightDao fd = new FlightDao(emf);
		UserDao ud = new UserDao(emf);
//		PaymentMethodDao pd = new PaymentMethodDao(emf);

//		User andrew = new User("andrew", "password");
//		User kabir = new User("kabir", "password");
//		User denisse = new User("denisse", "password");
//		User karthik = new User("karthik", "password");
//		User brian = new User("brian", "password");
//
//		PaymentMethod wechat = new PaymentMethod("wx-8888-8888", "wechat");
//		PaymentMethod alipay = new PaymentMethod("ali-8888-8888", "alipay");
//		PaymentMethod hsbc = new PaymentMethod("4023-8888-8888", "credit card", Date.valueOf("2020-08-08"));
//
//		andrew.addPaymentMethod(wechat);
//		kabir.addPaymentMethod(hsbc);
//
//		ud.add(andrew);
//		ud.add(kabir);
//		ud.add(denisse);
//		
//		User user = ud.getById(1);
////		System.out.println(user);
//		
////		ud.remove(denisse.getUserId());
//		
//		System.out.println(ud.getByUsername("andrew").toString());
//
//		ud.addPaymentMethod(andrew, alipay);
//		ud.updatePassword(andrew, "new pass");
//		ud.removePaymentMethod(andrew, wechat);
//
//		ud.updatePaymentMethodExpirationDate(kabir, hsbc, "2025-06-06");
//		
//		Booking b1 = new Booking(1000.00, Date.valueOf("2020-06-06"));
//		Booking b2 = new Booking(2000.00, Date.valueOf("2010-06-06"));
//		Booking b3 = new Booking(500.00, Date.valueOf("2015-06-06"));
//		
//		pd.addBooking(alipay, b1);
//		pd.addBooking(alipay, b2);
//		pd.addBooking(hsbc, b3);
//		
////		pd.removeBooking(alipay, b2);
//		
////		System.out.println(pd.getById("4023-8888-8888"));
////		System.out.println(pd.getBookings(alipay));
//		
//		FlightTicket ft1 = new FlightTicket("economy", "1a", Date.valueOf("2020-08-08"), 900.00);
//		FlightTicket ft2 = new FlightTicket("economy", "1a", Date.valueOf("2020-08-08"), 1800.00);
//		FlightTicket ft3 = new FlightTicket("economy", "1a", Date.valueOf("2020-08-08"), 400.00);
		
//		b1.addFlightTicket(ft1);
//		b2.addFlightTicket(ft2);
//		b3.addFlightTicket(ft3);
//		
//		PassengerDao pdao = new PassengerDao(emf);
//		
//		Passenger andrewPass = new Passenger("andrew", null, "doe", "test123@email.com", "666666", "ya006666");
//		Passenger kabirPass = new Passenger("kabir", null, "doe", "test456@email.com", "666666", "ya006667");
//		Passenger denissePass = new Passenger("kabir", null, "doe", "test789@email.com", "666666", "ya006668");	
//		
//		pdao.add(andrewPass);
//		pdao.add(kabirPass);
//		pdao.add(denissePass);
//		
//		andrewPass.addFlightTicket(ft1);
//		kabirPass.addFlightTicket(ft2);
//		denissePass.addFlightTicket(ft3);
//		
//		denissePass.removeFlightTicket(ft3);
////		System.out.println(andrewPass.toString());
//		
//		Flight f1 = new Flight("12:30", "14:30", 100);
//		Flight f2 = new Flight("12:30", "00:30", 100);
//		
//		Airport mxp = new Airport("MXP", "Milano Malpensa", "Milan", "Italy");
//		Airport pvg = new Airport("PVG", "Shanghai Pudong", "Shanghai", "China");
//		Airport hkg = new Airport("HKG", "Hong Kong", "Hong Kong", "");
//		Airport hng = new Airport("PVG", "Hangzhou", "Hangzhou", "China");
//		pvg.addFlightToOrigin(f1);
//		hkg.addFlightToDestination(f1);
//		hng.addFlightToDestination(f2);
//		mxp.addFlightToOrigin(f2);
//		
//		f1.addFlightTicket(ft1);
//		f2.addFlightTicket(ft2);
//		f1.addFlightTicket(ft3);
		
//		System.out.println(f1);
//		System.out.println(mxp);
//		FileInputStream file = new FileInputStream("H://Airports.csv");
//		InputStreamReader in = new InputStreamReader(file);
//		BufferedReader br = new BufferedReader(in);
//
//		String currentLine;
//		while((currentLine = br.readLine()) != null) {
//			String[] airportInfo = currentLine.split(",");
//			Airport returnedAirport = ad.getById(airportInfo[4]);
//			if(!airportInfo[4].equals("\\N") && returnedAirport == null) {
//				//iataId, name, city, country
//				Airport airport = new Airport(airportInfo[4], airportInfo[1], airportInfo[2], airportInfo[3]);
//				ad.add(airport);
//			}
//		}
//		br.close();
//		Airport HKG = ad.getById("HKG");
//		Airport PVG = ad.getById("PVG");
//		Airport JFK = ad.getById("JFK");
//		Airport MXP = ad.getById("MXP");
//		Flight HKGPVGMorning = new Flight("7:40", "9:50",380);
//		ad.addFlightToOrigin(HKG, HKGPVGMorning);
//		ad.addFlightToDestination(PVG, HKGPVGMorning);
//		
//		Flight HKGPVGAfternoon = new Flight("12:30", "14:50",380);
//		ad.addFlightToOrigin(HKG, HKGPVGAfternoon);
//		ad.addFlightToDestination(PVG, HKGPVGAfternoon);
//		
//		Flight HKGPVGEvening = new Flight("18:40", "21:20",380);
//		ad.addFlightToOrigin(HKG, HKGPVGEvening);
//		ad.addFlightToDestination(PVG, HKGPVGEvening);
//		
//		Flight HKGJFK = new Flight("15:25", "22:00", 480);
//		ad.addFlightToOrigin(HKG, HKGJFK);
//		ad.addFlightToDestination(JFK, HKGJFK);
//		
//		Flight JFKHKG = new Flight("9:00", "14:10", 480);
//		ad.addFlightToOrigin(JFK, JFKHKG);
//		ad.addFlightToDestination(HKG, JFKHKG);
//		
//		Flight PVGHKGMorning = new Flight("7:50", "10:00",380);
//		ad.addFlightToOrigin(PVG, PVGHKGMorning);
//		ad.addFlightToDestination(HKG, PVGHKGMorning);
//	
//		Flight PVGHKGAfternoon = new Flight("13:00", "15:20",380);
//		ad.addFlightToOrigin(PVG, PVGHKGAfternoon);
//		ad.addFlightToDestination(HKG, PVGHKGAfternoon);
//		
//		Flight PVGHKGEvening = new Flight("20:40", "23:20",380);
//		ad.addFlightToOrigin(PVG, PVGHKGEvening);
//		ad.addFlightToDestination(HKG, PVGHKGEvening);
//		
//		Flight HKGMXP = new Flight("00:55", "06:45", 580);
//		ad.addFlightToOrigin(HKG, HKGMXP);
//		ad.addFlightToDestination(MXP, HKGMXP);
//		
//		Flight MXPHKG = new Flight("12:30", "06:55", 580);
//		ad.addFlightToOrigin(MXP, MXPHKG);
//		ad.addFlightToDestination(HKG, MXPHKG);
//		
//		FlightTicket PVGHKGMorning2212 = new FlightTicket("Economy", Date.valueOf("2018-12-22"), 688.88);
//		FlightTicket PVGHKGAfternoon2212 = new FlightTicket("Economy", Date.valueOf("2018-12-22"), 788.88);
//		FlightTicket PVGHKGEvening2212 = new FlightTicket("Economy", Date.valueOf("2018-12-22"), 708.88);
//		fd.addFlightTicket(PVGHKGMorning,  PVGHKGMorning2212);
//		fd.addFlightTicket(PVGHKGAfternoon,  PVGHKGAfternoon2212);
//		fd.addFlightTicket(PVGHKGEvening,  PVGHKGEvening2212);
//		
//		FlightTicket PVGHKGMorning2312 = new FlightTicket("Economy", Date.valueOf("2018-12-23"), 688.88);
//		FlightTicket PVGHKGAfternoon2312 = new FlightTicket("Economy", Date.valueOf("2018-12-23"), 788.88);
//		FlightTicket PVGHKGEvening2312 = new FlightTicket("Economy", Date.valueOf("2018-12-23"), 708.88);
//		fd.addFlightTicket(PVGHKGMorning,  PVGHKGMorning2312);
//		fd.addFlightTicket(PVGHKGAfternoon,  PVGHKGAfternoon2312);
//		fd.addFlightTicket(PVGHKGEvening,  PVGHKGEvening2312);
//	
//		FlightTicket PVGHKGMorning2412 = new FlightTicket("Economy", Date.valueOf("2018-12-24"), 688.88);
//		FlightTicket PVGHKGAfternoon2412 = new FlightTicket("Economy", Date.valueOf("2018-12-24"), 788.88);
//		FlightTicket PVGHKGEvening2412 = new FlightTicket("Economy", Date.valueOf("2018-12-24"), 708.88);
//		fd.addFlightTicket(PVGHKGMorning,  PVGHKGMorning2412);
//		fd.addFlightTicket(PVGHKGAfternoon,  PVGHKGAfternoon2412);
//		fd.addFlightTicket(PVGHKGEvening,  PVGHKGEvening2412);
//		
//		FlightTicket PVGHKGMorning2512 = new FlightTicket("Economy", Date.valueOf("2018-12-25"), 688.88);
//		FlightTicket PVGHKGAfternoon2512 = new FlightTicket("Economy", Date.valueOf("2018-12-25"), 788.88);
//		FlightTicket PVGHKGEvening2512 = new FlightTicket("Economy", Date.valueOf("2018-12-25"), 708.88);
//		fd.addFlightTicket(PVGHKGMorning,  PVGHKGMorning2512);
//		fd.addFlightTicket(PVGHKGAfternoon,  PVGHKGAfternoon2512);
//		fd.addFlightTicket(PVGHKGEvening,  PVGHKGEvening2512);
//		
//		FlightTicket PVGHKGMorning2612 = new FlightTicket("Economy", Date.valueOf("2018-12-26"), 688.88);
//		FlightTicket PVGHKGAfternoon2612 = new FlightTicket("Economy", Date.valueOf("2018-12-26"), 788.88);
//		FlightTicket PVGHKGEvening2612 = new FlightTicket("Economy", Date.valueOf("2018-12-26"), 708.88);
//		fd.addFlightTicket(PVGHKGMorning,  PVGHKGMorning2612);
//		fd.addFlightTicket(PVGHKGAfternoon,  PVGHKGAfternoon2612);
//		fd.addFlightTicket(PVGHKGEvening,  PVGHKGEvening2612);
//		
//		FlightTicket PVGHKGMorning2712 = new FlightTicket("Economy", Date.valueOf("2018-12-27"), 688.88);
//		FlightTicket PVGHKGAfternoon2712 = new FlightTicket("Economy", Date.valueOf("2018-12-27"), 788.88);
//		FlightTicket PVGHKGEvening2712 = new FlightTicket("Economy", Date.valueOf("2018-12-27"), 708.88);
//		fd.addFlightTicket(PVGHKGMorning,  PVGHKGMorning2712);
//		fd.addFlightTicket(PVGHKGAfternoon,  PVGHKGAfternoon2712);
//		fd.addFlightTicket(PVGHKGEvening,  PVGHKGEvening2712);
//		
//		FlightTicket PVGHKGMorning2812 = new FlightTicket("Economy", Date.valueOf("2018-12-28"), 688.88);
//		FlightTicket PVGHKGAfternoon2812 = new FlightTicket("Economy", Date.valueOf("2018-12-28"), 788.88);
//		FlightTicket PVGHKGEvening2812 = new FlightTicket("Economy", Date.valueOf("2018-12-28"), 708.88);
//		fd.addFlightTicket(PVGHKGMorning,  PVGHKGMorning2812);
//		fd.addFlightTicket(PVGHKGAfternoon,  PVGHKGAfternoon2812);
//		fd.addFlightTicket(PVGHKGEvening,  PVGHKGEvening2812);
//		
//		FlightTicket PVGHKGMorning2912 = new FlightTicket("Economy", Date.valueOf("2018-12-29"), 688.88);
//		FlightTicket PVGHKGAfternoon2912 = new FlightTicket("Economy", Date.valueOf("2018-12-29"), 788.88);
//		FlightTicket PVGHKGEvening2912 = new FlightTicket("Economy", Date.valueOf("2018-12-29"), 708.88);
//		fd.addFlightTicket(PVGHKGMorning,  PVGHKGMorning2912);
//		fd.addFlightTicket(PVGHKGAfternoon,  PVGHKGAfternoon2912);
//		fd.addFlightTicket(PVGHKGEvening,  PVGHKGEvening2912);
//		
//		FlightTicket PVGHKGMorning3012 = new FlightTicket("Economy", Date.valueOf("2018-12-30"), 688.88);
//		FlightTicket PVGHKGAfternoon3012 = new FlightTicket("Economy", Date.valueOf("2018-12-30"), 788.88);
//		FlightTicket PVGHKGEvening3012 = new FlightTicket("Economy", Date.valueOf("2018-12-30"), 708.88);
//		fd.addFlightTicket(PVGHKGMorning,  PVGHKGMorning3012);
//		fd.addFlightTicket(PVGHKGAfternoon,  PVGHKGAfternoon3012);
//		fd.addFlightTicket(PVGHKGEvening,  PVGHKGEvening3012);
//		
//		FlightTicket PVGHKGMorning3112 = new FlightTicket("Economy", Date.valueOf("2018-12-31"), 688.88);
//		FlightTicket PVGHKGAfternoon3112 = new FlightTicket("Economy", Date.valueOf("2018-12-31"), 788.88);
//		FlightTicket PVGHKGEvening3112 = new FlightTicket("Economy", Date.valueOf("2018-12-31"), 708.88);
//		fd.addFlightTicket(PVGHKGMorning,  PVGHKGMorning3112);
//		fd.addFlightTicket(PVGHKGAfternoon,  PVGHKGAfternoon3112);
//		fd.addFlightTicket(PVGHKGEvening,  PVGHKGEvening3112);
//		
//		FlightTicket HKGPVGMorning2212 = new FlightTicket("Economy", Date.valueOf("2018-12-22"), 688.88);
//		FlightTicket HKGPVGAfternoon2212 = new FlightTicket("Economy", Date.valueOf("2018-12-22"), 788.88);
//		FlightTicket HKGPVGEvening2212 = new FlightTicket("Economy", Date.valueOf("2018-12-22"), 708.88);
//		fd.addFlightTicket(HKGPVGMorning,  HKGPVGMorning2212);
//		fd.addFlightTicket(HKGPVGAfternoon,  HKGPVGAfternoon2212);
//		fd.addFlightTicket(HKGPVGEvening,  HKGPVGEvening2212);
//		
//		FlightTicket HKGPVGMorning2312 = new FlightTicket("Economy", Date.valueOf("2018-12-23"), 688.88);
//		FlightTicket HKGPVGAfternoon2312 = new FlightTicket("Economy", Date.valueOf("2018-12-23"), 788.88);
//		FlightTicket HKGPVGEvening2312 = new FlightTicket("Economy", Date.valueOf("2018-12-23"), 708.88);
//		fd.addFlightTicket(HKGPVGMorning,  HKGPVGMorning2312);
//		fd.addFlightTicket(HKGPVGAfternoon,  HKGPVGAfternoon2312);
//		fd.addFlightTicket(HKGPVGEvening,  HKGPVGEvening2312);
//	
//		FlightTicket HKGPVGMorning2412 = new FlightTicket("Economy", Date.valueOf("2018-12-24"), 688.88);
//		FlightTicket HKGPVGAfternoon2412 = new FlightTicket("Economy", Date.valueOf("2018-12-24"), 788.88);
//		FlightTicket HKGPVGEvening2412 = new FlightTicket("Economy", Date.valueOf("2018-12-24"), 708.88);
//		fd.addFlightTicket(HKGPVGMorning,  HKGPVGMorning2412);
//		fd.addFlightTicket(HKGPVGAfternoon,  HKGPVGAfternoon2412);
//		fd.addFlightTicket(HKGPVGEvening,  HKGPVGEvening2412);
//		
//		FlightTicket HKGPVGMorning2512 = new FlightTicket("Economy", Date.valueOf("2018-12-25"), 688.88);
//		FlightTicket HKGPVGAfternoon2512 = new FlightTicket("Economy", Date.valueOf("2018-12-25"), 788.88);
//		FlightTicket HKGPVGEvening2512 = new FlightTicket("Economy", Date.valueOf("2018-12-25"), 708.88);
//		fd.addFlightTicket(HKGPVGMorning,  HKGPVGMorning2512);
//		fd.addFlightTicket(HKGPVGAfternoon,  HKGPVGAfternoon2512);
//		fd.addFlightTicket(HKGPVGEvening,  HKGPVGEvening2512);
//		
//		FlightTicket HKGPVGMorning2612 = new FlightTicket("Economy", Date.valueOf("2018-12-26"), 688.88);
//		FlightTicket HKGPVGAfternoon2612 = new FlightTicket("Economy", Date.valueOf("2018-12-26"), 788.88);
//		FlightTicket HKGPVGEvening2612 = new FlightTicket("Economy", Date.valueOf("2018-12-26"), 708.88);
//		fd.addFlightTicket(HKGPVGMorning,  HKGPVGMorning2612);
//		fd.addFlightTicket(HKGPVGAfternoon,  HKGPVGAfternoon2612);
//		fd.addFlightTicket(HKGPVGEvening,  HKGPVGEvening2612);
//		
//		FlightTicket HKGPVGMorning2712 = new FlightTicket("Economy", Date.valueOf("2018-12-27"), 688.88);
//		FlightTicket HKGPVGAfternoon2712 = new FlightTicket("Economy", Date.valueOf("2018-12-27"), 788.88);
//		FlightTicket HKGPVGEvening2712 = new FlightTicket("Economy", Date.valueOf("2018-12-27"), 708.88);
//		fd.addFlightTicket(HKGPVGMorning,  HKGPVGMorning2712);
//		fd.addFlightTicket(HKGPVGAfternoon,  HKGPVGAfternoon2712);
//		fd.addFlightTicket(HKGPVGEvening,  HKGPVGEvening2712);
//		
//		FlightTicket HKGPVGMorning2812 = new FlightTicket("Economy", Date.valueOf("2018-12-28"), 688.88);
//		FlightTicket HKGPVGAfternoon2812 = new FlightTicket("Economy", Date.valueOf("2018-12-28"), 788.88);
//		FlightTicket HKGPVGEvening2812 = new FlightTicket("Economy", Date.valueOf("2018-12-28"), 708.88);
//		fd.addFlightTicket(HKGPVGMorning,  HKGPVGMorning2812);
//		fd.addFlightTicket(HKGPVGAfternoon,  HKGPVGAfternoon2812);
//		fd.addFlightTicket(HKGPVGEvening,  HKGPVGEvening2812);
//		
//		FlightTicket HKGPVGMorning2912 = new FlightTicket("Economy", Date.valueOf("2018-12-29"), 688.88);
//		FlightTicket HKGPVGAfternoon2912 = new FlightTicket("Economy", Date.valueOf("2018-12-29"), 788.88);
//		FlightTicket HKGPVGEvening2912 = new FlightTicket("Economy", Date.valueOf("2018-12-29"), 708.88);
//		fd.addFlightTicket(HKGPVGMorning,  HKGPVGMorning2912);
//		fd.addFlightTicket(HKGPVGAfternoon,  HKGPVGAfternoon2912);
//		fd.addFlightTicket(HKGPVGEvening,  HKGPVGEvening2912);
//		
//		FlightTicket HKGPVGMorning3012 = new FlightTicket("Economy", Date.valueOf("2018-12-30"), 688.88);
//		FlightTicket HKGPVGAfternoon3012 = new FlightTicket("Economy", Date.valueOf("2018-12-30"), 788.88);
//		FlightTicket HKGPVGEvening3012 = new FlightTicket("Economy", Date.valueOf("2018-12-30"), 708.88);
//		fd.addFlightTicket(HKGPVGMorning,  HKGPVGMorning3012);
//		fd.addFlightTicket(HKGPVGAfternoon,  HKGPVGAfternoon3012);
//		fd.addFlightTicket(HKGPVGEvening,  HKGPVGEvening3012);
//		
//		FlightTicket HKGPVGMorning3112 = new FlightTicket("Economy", Date.valueOf("2018-12-31"), 688.88);
//		FlightTicket HKGPVGAfternoon3112 = new FlightTicket("Economy", Date.valueOf("2018-12-31"), 788.88);
//		FlightTicket HKGPVGEvening3112 = new FlightTicket("Economy", Date.valueOf("2018-12-31"), 708.88);
//		fd.addFlightTicket(HKGPVGMorning,  HKGPVGMorning3112);
//		fd.addFlightTicket(HKGPVGAfternoon,  HKGPVGAfternoon3112);
//		fd.addFlightTicket(HKGPVGEvening,  HKGPVGEvening3112);
		
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//		Date date = (Date) format.parse("2018-12-25");
//		DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yy");
//		System.out.println(format.parse("2018-12-25"));
//		String queryString = "SELECT a.name, a.airportIATAId, a.city FROM Airport a WHERE a.city LIKE 'Shanghai%'";
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		Query query = em.createQuery(queryString);
//		List<Object[]> result = query.getResultList();
//		for (Object[] r : result) {
//			System.out.println(r[0] + " " + r[1] + " " + r[2]);
//		}
//		em.close();
//		ud.add(new User("andrew", "pw"));
//		Passenger p1 = new Passenger("Karthik", "Asokan", "HK123456");
//		Passenger p2 = new Passenger("Kabir", "Rajput", "IN123456");
//		ud.addPassenger("andrew", p1);
//		ud.addPassenger("andrew", p2);
//		String queryString = "SELECT p.firstName, p.middleName, p.lastName, p.email, p.mobile, p.passportNo from User u LEFT JOIN u.passengers p WHERE u.username = 'andrew'";
//		EntityManager em = emf.createEntityManager();
//		EntityTransaction et = em.getTransaction();
//
//		et.begin();
//		Query query = em.createQuery(queryString);
//		List<String[]> passengersData = query.getResultList();
//		et.commit();
//		em.close();
	}
}
