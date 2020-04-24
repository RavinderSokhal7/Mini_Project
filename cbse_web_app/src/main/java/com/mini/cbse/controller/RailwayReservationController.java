package com.mini.cbse.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mini.cbse.Category.CategoryJDBCTemplate;
import com.mini.cbse.Train.Train;
import com.mini.cbse.Train.TrainJDBCTemplate;
import com.mini.cbse.Train.TrainSchedule;
import com.mini.cbse.User.User;
import com.mini.cbse.User.UserJDBCTemplate;

@Controller
public class RailwayReservationController {
	ApplicationContext context = new ClassPathXmlApplicationContext("DataSource.xml");
//	BookJDBCTemplate bookJDBCTemplate = (BookJDBCTemplate)context.getBean("bookJDBCTemplate");
	UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
	CategoryJDBCTemplate categoryJDBCTemplate = (CategoryJDBCTemplate)context.getBean("categoryJDBCTemplate");
	TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate)context.getBean("trainJDBCTemplate");
	
	@RequestMapping(value="/train-select-city", method = RequestMethod.POST)
	public ModelAndView getCities(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView("constructed_page");
		String fromCity = request.getParameter("fromCity");
		String toCity = request.getParameter("toCity");
		String catname = (String)session.getAttribute("category_name");
		String counter_username = (String) session.getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user);
		List<String> list = (List<String>)session.getAttribute("components");
		mv.addObject("components", list);
		mv.addObject("category",catname);
		if(fromCity!=null && toCity!=null) {
			mv.addObject("fromCity", fromCity);
			mv.addObject("toCity", toCity);
//			System.out.println(fromCity+toCity);
			session.setAttribute("fromCity", fromCity);
			session.setAttribute("toCity", toCity);
		}
		else if(fromCity!=null) {
			mv.addObject("fromCity", fromCity);
			session.setAttribute("fromCity", fromCity);
//			System.out.println("to NULL");
		}
		else if(toCity!=null){
			mv.addObject("toCity", toCity);
			session.setAttribute("toCity", toCity);
//			System.out.println("from Null");
		}
		else {
//			System.out.println("Both NULL");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/get-trains", method = RequestMethod.GET)
	public ModelAndView getTrainsGet(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView("constructed_page");
		String fromCity = (String)session.getAttribute("fromCity");
		String toCity = (String)session.getAttribute("toCity");
		String catname = (String)session.getAttribute("category_name");
		String counter_username = (String) session.getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user);
		List<String> list = (List<String>)session.getAttribute("components");
		List<String> bookTicket = (List<String>)session.getAttribute("bookTicket");
		mv.addObject("bookTicket",bookTicket);
		mv.addObject("components", list);
		mv.addObject("category",catname);
		if(fromCity!=null && toCity!=null) {
			List<TrainSchedule> ts = trainJDBCTemplate.getTrainsByFromToStation(fromCity.toLowerCase(), toCity.toLowerCase());
			if(ts.size()==0) {
				mv.addObject("status", false);
				mv.addObject("errorMessage", "No Trains Found!");
			}
			else {
				for(int i=0;i<ts.size();i++) {
				int tno = ts.get(i).getTrain_no();
				ts.get(i).setTrain_name(trainJDBCTemplate.getTrainByTno(tno).getTrain_name());
			}
			mv.addObject("trainSchedules", ts);
			}
			
		}
		else if(fromCity!=null) {
//			List<TrainSchedule> ts = trainJDBCTemplate.getTrainsByOnlyFromStation(fromCity.toLowerCase());
//			mv.addObject("trainSchedules", ts);
			System.out.println("to NULL");
		}
		else if(toCity!=null){
//			List<TrainSchedule> ts = trainJDBCTemplate.getTrainsByOnlyToStation( toCity.toLowerCase());
//			mv.addObject("trainSchedules", ts);
			System.out.println("from Null");
		}
		else {
			System.out.println("Both NULL");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/get-trains", method = RequestMethod.POST)
	public ModelAndView getTrains(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView("constructed_page");
		String fromCity = (String)request.getParameter("fromCity");
		String toCity = (String)request.getParameter("toCity");
		String catname = (String)session.getAttribute("category_name");
		String counter_username = (String) session.getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user);
		List<String> list = (List<String>)session.getAttribute("components");
		List<String> bookTicket = (List<String>)session.getAttribute("bookTicket");
		mv.addObject("bookTicket",bookTicket);
		mv.addObject("components", list);
		mv.addObject("category",catname);
		if(fromCity!=null && toCity!=null) {
			List<TrainSchedule> ts = trainJDBCTemplate.getTrainsByFromToStation(fromCity.toLowerCase(), toCity.toLowerCase());
			if(ts.size()==0) {
				mv.addObject("status", false);
				mv.addObject("errorMessage", "No Trains Found!");
			}
			else {
				for(int i=0;i<ts.size();i++) {
					int tno = ts.get(i).getTrain_no();
					ts.get(i).setTrain_name(trainJDBCTemplate.getTrainByTno(tno).getTrain_name());
				}
				mv.addObject("trainSchedules", ts);
			}
			
			mv.addObject("fromCity",fromCity);
			mv.addObject("toCity",toCity);
		}
		else if(fromCity!=null) {
//			List<TrainSchedule> ts = trainJDBCTemplate.getTrainsByOnlyFromStation(fromCity.toLowerCase());
//			mv.addObject("trainSchedules", ts);
			System.out.println("to NULL");
		}
		else if(toCity!=null){
//			List<TrainSchedule> ts = trainJDBCTemplate.getTrainsByOnlyToStation( toCity.toLowerCase());
//			mv.addObject("trainSchedules", ts);
			System.out.println("from Null");
		}
		else {
			System.out.println("Both NULL");
		}
		
		return mv;
	}

	@RequestMapping(value="/book-train", method = RequestMethod.POST)
	public ModelAndView bookTrain(HttpServletRequest request, @RequestParam(value="train_no") int train_no) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView("BookTrainPage");
		String fromCity = (String)session.getAttribute("fromCity");
		String toCity = (String)session.getAttribute("toCity");
		String catname = (String)session.getAttribute("category_name");
		String counter_username = (String) session.getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user);
		Train train = trainJDBCTemplate.getTrainByTno(train_no);
		List<TrainSchedule> tranSch= trainJDBCTemplate.getTrainsByFromToStation(fromCity, toCity);
		TrainSchedule ts=null;
		for(int i=0;i<tranSch.size();i++) {
			ts = tranSch.get(i);
			if(ts.getTrain_no()==train.getTrain_no()) break;
		}
		ts.setTrain_name(train.getTrain_name());
		session.setAttribute("from_no", ts.getFrom_no());
		session.setAttribute("to_no", ts.getTo_no());
		mv.addObject("ts", ts);
		mv.addObject("train", train);
		mv.addObject("category", catname);
		mv.addObject("fromCity",fromCity);
		mv.addObject("toCity",toCity);
		
		return mv;
	}

	@RequestMapping(value = "/book-train-ticket", method = RequestMethod.POST)
	public ModelAndView bookTrainTicket(HttpServletRequest request,
//			@RequestParam(value="date") String date,
			@RequestParam(value="selectedClass") String selectedClass,
			@RequestParam(value="adult")int adult,
			@RequestParam(value="child")int child,
			@RequestParam(value="train_no")int train_no) {
		HttpSession session = request.getSession();
		String[] datearr = request.getParameterValues("selected_date");
		String date = datearr[0];
		boolean status=false;
		String msg = null;
		int f=0;
		ModelAndView mv = new ModelAndView("BookTrainPage");
		String fromCity = (String)session.getAttribute("fromCity");
		String toCity = (String)session.getAttribute("toCity");
		String catname = (String)session.getAttribute("category_name");
		String counter_username = (String) session.getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user);
		Train train = trainJDBCTemplate.getTrainByTno(train_no);
		List<TrainSchedule> tranSch= trainJDBCTemplate.getTrainsByFromToStation(fromCity, toCity);
		TrainSchedule ts=null;
		for(int i=0;i<tranSch.size();i++) {
			ts = tranSch.get(i);
			if(ts.getTrain_no()==train.getTrain_no()) { f=1; ts.setTrain_name(train.getTrain_name()); break;}
		}
		/*
		 * Check availability and Calculate Cost
		 */
		int passengers = adult+child;
		if(passengers>0 && f==1) {
			int av_seat = trainJDBCTemplate.getAvailableSeats(train_no);
			if(passengers <= av_seat) {
				int farepp = trainJDBCTemplate.getTicketCost(train_no,ts.getFrom_no(),ts.getTo_no(),selectedClass.toLowerCase());
				
				if(farepp == -1) System.out.println("Train_cost table need to be updated for this transaction");
				
				int total_cost = (passengers)*farepp;
				int reservation_id = trainJDBCTemplate.
						makeReservation(user.getUsername(),user.getName(),ts,date,selectedClass.toLowerCase(),adult,child,total_cost);
				mv.setViewName("TrainBillPage");
				mv.addObject("farepp", farepp);
				mv.addObject("total_cost",total_cost);
				mv.addObject("reservation_id", reservation_id);
				mv.addObject("date", date);
				mv.addObject("selectedClass", selectedClass);
				mv.addObject("adult", adult);
				mv.addObject("child", child);
				
				status = true;
				msg = "Bill Generated successfully";
			}
			else {
				msg = "seats not available!"+"only "+av_seat+" left!";
			}
			
		}
		else {
			msg="Select Passengers(>1) and select From to city (or No trains Available for your request)!";
		}
		
		
		//
		mv.addObject("ts", ts);
		mv.addObject("train", train);
		mv.addObject("category", catname);
		mv.addObject("status", status);
		mv.addObject("errorMessage",msg);
		return mv;
	}
	
	@RequestMapping(value = "/book-train-ticket-from-constructed-page", method = RequestMethod.POST)
	public ModelAndView bookTrainTicketFromConstructedPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<String> bookTicket = (List<String>)session.getAttribute("bookTicket");
		if( !bookTicket.contains("SelectTrainNoComp.jsp") || !bookTicket.contains("SelectDateComp.jsp")
				|| !bookTicket.contains("SelectClassComp.jsp") || !bookTicket.contains("SelectPassengerComp.jsp")) {
			ModelAndView mv = new ModelAndView("constructed_page");
			List<String> list = (List<String>)session.getAttribute("components");
			String catname = (String)session.getAttribute("category_name");
			String counter_username = (String) session.getAttribute("user");
			User user = userJDBCTemplate.getUser(counter_username);
			if(user == null) { return new ModelAndView("redirect:logout"); }
			mv.addObject("user", user);
			mv.addObject("category", catname);
			mv.addObject("components",list);
			mv.addObject("bookTicket", bookTicket);
			mv.addObject("errorMessage", "More Information Needed To Book Ticket!"
					+ "\nTry Book Option in Get Trains or"
					+ "\nTry Selecting all Components :)");
			mv.addObject("status", false);
			return mv;
		}
		
		int f=0;
		String[] datearr = request.getParameterValues("selected_date");
		String date = datearr[0];
		// Get Params
		String selectedClass = request.getParameter("selectedClass");
		int adult = Integer.parseInt(request.getParameter("adult"));
		int child = Integer.parseInt(request.getParameter("child"));
		int train_no = Integer.parseInt(request.getParameter("train_no"));
		
		boolean status=false;
		String msg = null;
		ModelAndView mv = new ModelAndView("constructed_page");
		String fromCity = (String)session.getAttribute("fromCity");
		String toCity = (String)session.getAttribute("toCity");
		String catname = (String)session.getAttribute("category_name");
		String counter_username = (String) session.getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user);
		Train train = trainJDBCTemplate.getTrainByTno(train_no);
		List<TrainSchedule> tranSch= trainJDBCTemplate.getTrainsByFromToStation(fromCity, toCity);
		TrainSchedule ts=null;
		for(int i=0;i<tranSch.size();i++) {
			ts = tranSch.get(i);
			if(ts.getTrain_no()==train.getTrain_no()) { f=1;ts.setTrain_name(train.getTrain_name()); break;}
		}
		
		/*
		 * Check availability and Calculate Cost
		 */
		int passengers = adult+child;
		if(passengers>0 && f==1) {
			int av_seat = trainJDBCTemplate.getAvailableSeats(train_no);
			if(passengers <= av_seat) {
				int farepp = trainJDBCTemplate.getTicketCost(train_no,ts.getFrom_no(),ts.getTo_no(),selectedClass.toLowerCase());
				
				if(farepp == -1) System.out.println("Train_cost table need to be updated for this transaction");
				
				int total_cost = (passengers)*farepp;
				int reservation_id = trainJDBCTemplate.
						makeReservation(user.getUsername(),user.getName(),ts,date,selectedClass.toLowerCase(),adult,child,total_cost);
				mv.setViewName("TrainBillPage");
				mv.addObject("farepp", farepp);
				mv.addObject("total_cost",total_cost);
				mv.addObject("reservation_id", reservation_id);
				mv.addObject("date", date);
				mv.addObject("selectedClass", selectedClass);
				mv.addObject("adult", adult);
				mv.addObject("child", child);
				
				status = true;
				msg = "Bill Generated successfully";
			}
			else {
				msg = "seats not available!"+"only "+av_seat+" left!";
			}
			
		}
		else {
			msg="Select Passengers(>1) and select From to city (or No trains Available for your request)!";
		}
		
		//
		mv.addObject("ts", ts);
		mv.addObject("train", train);
		mv.addObject("category", catname);
		mv.addObject("status", status);
		mv.addObject("errorMessage",msg);
		return mv;
	}
	
}




















