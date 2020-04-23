package com.mini.cbse.controller;

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
		mv.addObject("components", list);
		mv.addObject("category",catname);
		if(fromCity!=null && toCity!=null) {
			List<TrainSchedule> ts = trainJDBCTemplate.getTrainsByFromToStation(fromCity.toLowerCase(), toCity.toLowerCase());
			if(ts.size()==0) {
				mv.addObject("status", false);
				mv.addObject("errorMessage", "No Trains Found!");
			}
			else {
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
		mv.addObject("components", list);
		mv.addObject("category",catname);
		if(fromCity!=null && toCity!=null) {
			List<TrainSchedule> ts = trainJDBCTemplate.getTrainsByFromToStation(fromCity.toLowerCase(), toCity.toLowerCase());
			if(ts.size()==0) {
				mv.addObject("status", false);
				mv.addObject("errorMessage", "No Trains Found!");
			}
			else {
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
		mv.addObject("train", train);
		mv.addObject("category", catname);
		mv.addObject("fromCity",fromCity);
		mv.addObject("toCity",toCity);
		
		return mv;
	}

}




















