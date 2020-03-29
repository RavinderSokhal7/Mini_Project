package services;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import controller.AppConfig;
import interfaces.UserDao;
import models.UserBO;

@Service
public class registerService {

	private static ApplicationContext factory;

	public static ModelAndView registerUser(HttpServletRequest request, ModelAndView mv) throws ClassNotFoundException, SQLException {
		
		factory = new AnnotationConfigApplicationContext(AppConfig.class);
		
		String firstname = request.getParameter("first_name");
		String lastname = request.getParameter("last_name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String contactno = request.getParameter("contact");

		UserBO newuser = factory.getBean(UserBO.class);
		newuser.setAddress(address);
		newuser.setContactno(contactno);
		newuser.setFirstname(firstname);
		newuser.setLastname(lastname);
		newuser.setUsername(username);
		newuser.setPassword(password);
		UserDao udao = factory.getBean(UserDao.class);
		
		if(udao.registerUser(newuser)) {
			mv.setViewName("registerSuccess");
		}
		else {
			mv.setViewName("registerform");
		}

		return mv;
	}

}
