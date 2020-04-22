package com.mini.cbse.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.mini.cbse.Book.BookJDBCTemplate;
import com.mini.cbse.Category.CategoryJDBCTemplate;
import com.mini.cbse.Train.TrainJDBCTemplate;
import com.mini.cbse.User.UserJDBCTemplate;

@Controller
public class RailwayReservationController {
	ApplicationContext context = new ClassPathXmlApplicationContext("DataSource.xml");
//	BookJDBCTemplate bookJDBCTemplate = (BookJDBCTemplate)context.getBean("bookJDBCTemplate");
	UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
	CategoryJDBCTemplate categoryJDBCTemplate = (CategoryJDBCTemplate)context.getBean("categoryJDBCTemplate");
	TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate)context.getBean("trainJDBCTemplate");

}
