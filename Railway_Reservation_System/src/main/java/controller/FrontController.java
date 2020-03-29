package controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.loginService;
import services.registerService;

@Controller
public class FrontController {
	
	@RequestMapping("/login")
	public ModelAndView checkUser(@RequestParam("username") String username,@RequestParam("password") String password,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		try {
			
			mv = loginService.checkUserForLogin(username,password,mv,request);
			
		} catch (Exception e) {
			
//			mv.addObject("exception", e.getMessage());
			mv.addObject("message", e.getMessage());
			mv.setViewName("error");
			
		}
		
		return mv;
	}
	
	@RequestMapping("/register")
	public ModelAndView registerUser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		try {
			mv = registerService.registerUser(request,mv);
			
		} catch (Exception e) {

			mv.setViewName("error");

		}
		
		return mv;
	}
}
