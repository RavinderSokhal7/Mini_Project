package com.mini.cbse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mini.cbse.Book.BookJDBCTemplate;
import com.mini.cbse.Category.CategoryJDBCTemplate;
import com.mini.cbse.Train.TrainJDBCTemplate;
import com.mini.cbse.User.UserJDBCTemplate;
import com.mini.cbse.components.AddBookComp;
import com.mini.cbse.components.Component;
import com.mini.cbse.components.EditBookComp;
import com.mini.cbse.components.IssueReturnComp;
import com.mini.cbse.components.ShowBooksComp;

@Controller
public class LibraryController {
	ApplicationContext context = new ClassPathXmlApplicationContext("DataSource.xml");
	BookJDBCTemplate bookJDBCTemplate = (BookJDBCTemplate)context.getBean("bookJDBCTemplate");
	UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
	CategoryJDBCTemplate categoryJDBCTemplate = (CategoryJDBCTemplate)context.getBean("categoryJDBCTemplate");
	TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate)context.getBean("trainJDBCTemplate");


	@RequestMapping(value="/books", method=RequestMethod.GET)
	public ModelAndView showBooks(HttpServletRequest request,@RequestParam(value = "search", required = false, defaultValue = "") String search) {
		Component comp = new ShowBooksComp();
		ModelAndView mv = new ModelAndView();
		Map<String,Object> parameter = new HashMap<String,Object> ();
	    parameter.put("search",search);
	    comp.doGetAction(request,parameter);
	    mv.setViewName(comp.getViewName());
	    mv.addAllObjects(comp.getModelMap());
	    String catname = (String) request.getSession().getAttribute("category_name");
		mv.addObject("category",catname);
	    return mv;
		
	}
	
	@RequestMapping(value="/issue-return", method=RequestMethod.GET)
	public ModelAndView issueReturnBook(HttpServletRequest request) {
		Component comp = new IssueReturnComp();
		ModelAndView mv = new ModelAndView();
	    comp.doGetAction(request,null);
	    mv.setViewName(comp.getViewName());
	    mv.addAllObjects(comp.getModelMap());
	    String catname = (String) request.getSession().getAttribute("category_name");
		mv.addObject("category",catname);
	    return mv;
	}
	
	@RequestMapping(value="/issue-return", method=RequestMethod.POST)
	public ModelAndView issueReturnForm(HttpServletRequest request,
			@RequestParam(value = "username", required = false, defaultValue = "") String username,
			@RequestParam(value = "bookid", required = false, defaultValue = "0") Integer bookid) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
	    Map<String,Object> parameter = new HashMap<String,Object> ();
	    parameter.put("username",username);
	    parameter.put("bookid",bookid);
	    
	    Component comp = new IssueReturnComp();
	    comp.doPostAction(request,parameter);
	    mv.setViewName(comp.getViewName());
	    mv.addAllObjects(comp.getModelMap());
	    String catname = (String) session.getAttribute("category_name");
		mv.addObject("category",catname);
		
	    return mv;
	}
	
	@RequestMapping(value="/add-book", method=RequestMethod.GET)
	public ModelAndView addBooksGet(HttpServletRequest request) {
		Component addbook = new AddBookComp();
		ModelAndView mv = new ModelAndView();
	    addbook.doGetAction(request,null);
	    mv.setViewName(addbook.getViewName());
	    mv.addAllObjects(addbook.getModelMap());
	    String catname = (String) request.getSession().getAttribute("category_name");
		mv.addObject("category",catname);
	    return mv;
		
	}
	@RequestMapping(value="/edit-book", method=RequestMethod.GET)
	public ModelAndView editBooksGet(HttpServletRequest request) {
		Component comp = new EditBookComp();
		ModelAndView mv = new ModelAndView();
	    comp.doGetAction(request,null);
	    mv.setViewName(comp.getViewName());
	    mv.addAllObjects(comp.getModelMap());
	    String catname = (String) request.getSession().getAttribute("category_name");
		mv.addObject("category",catname);
	    return mv;
		
	}
	
	@RequestMapping(value="/add-book", method=RequestMethod.POST)
	public ModelAndView addBooks(HttpServletRequest request,@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "author", required = false, defaultValue = "") String author,@RequestParam(value = "copies", required = false, defaultValue = "0") Integer copies) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
	    Map<String,Object> parameter = new HashMap<String,Object> ();
	    parameter.put("name",name);
	    parameter.put("author",author);
	    parameter.put("copies",copies);
	    
	    Component comp = new AddBookComp();
	    comp.doPostAction(request,parameter);
	    mv.setViewName(comp.getViewName());
	    mv.addAllObjects(comp.getModelMap());
	    String catname = (String) session.getAttribute("category_name");
		mv.addObject("category",catname);
	    return mv;

	}
	
	@RequestMapping(value="/edit-book", method=RequestMethod.POST)
	public ModelAndView editBooks(HttpServletRequest request,@RequestParam(value = "bookid", required = false, defaultValue = "-1") Integer bookid,
			@RequestParam(value = "total", required = false, defaultValue = "0") Integer total,@RequestParam(value = "rem", required = false, defaultValue = "0") Integer rem) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
	    Map<String,Object> parameter = new HashMap<String,Object> ();
	    parameter.put("bookid",bookid);
	    parameter.put("total",total);
	    parameter.put("rem",rem);
	    
	    Component comp = new EditBookComp();
	    comp.doPostAction(request,parameter);
	    mv.setViewName(comp.getViewName());
	    mv.addAllObjects(comp.getModelMap());
	    String catname = (String) session.getAttribute("category_name");
		mv.addObject("category",catname);
	    return mv;
	}
}
