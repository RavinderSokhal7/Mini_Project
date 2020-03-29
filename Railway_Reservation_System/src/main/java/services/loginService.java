package services;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import controller.AppConfig;
import interfaces.UserDao;
import models.UserBO;

@Service
public class loginService {

	private static ApplicationContext factory;

	public static ModelAndView checkUserForLogin(String username, String password, ModelAndView mv, HttpServletRequest request) throws ClassNotFoundException, SQLException {

		factory = new AnnotationConfigApplicationContext(AppConfig.class);
		
		HttpSession session = request.getSession();
//		ModelMap model = mv.getModelMap();
		//Persistence logic/ DAO logic 
    	
		UserDao udao = factory.getBean(UserDao.class);

		UserBO user = factory.getBean(UserBO.class);
		user.setUsername(username);
		user.setPassword(password);
		
    	if(session.getAttribute("username")!=null){
//    		user = udao.getUserDetails(username);
//    		model.addAttribute("user", user);
    		mv.setViewName("home");
    	}

		if(udao.checkUserForLogin(user)) {
			session.setAttribute("username", username);
//			user = udao.getUserDetails(username);
//    		model.addAttribute("user", user);
			mv.setViewName("home");
		}
		else {
			mv.setViewName("loginFailure");
		}
		return mv;
	}

}
