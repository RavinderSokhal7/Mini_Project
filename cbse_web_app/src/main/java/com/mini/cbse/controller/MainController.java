package com.mini.cbse.controller;

import java.util.Arrays;
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

import com.mini.cbse.Response;
import com.mini.cbse.Book.BookJDBCTemplate;
import com.mini.cbse.Category.Category;
import com.mini.cbse.Category.CategoryJDBCTemplate;
import com.mini.cbse.Category.Component;
//import com.mini.cbse.Options.optionsJDBCTemplate;
import com.mini.cbse.User.User;
import com.mini.cbse.User.UserJDBCTemplate;
import com.mini.cbse.Book.Book;
import com.mini.cbse.MenuLinks;
import com.mini.cbse.QuickLinks;

@Controller
public class MainController {
	QuickLinks ql = new QuickLinks();
	MenuLinks ml = new MenuLinks();
	ApplicationContext context = new ClassPathXmlApplicationContext("DataSource.xml");
	BookJDBCTemplate bookJDBCTemplate = (BookJDBCTemplate)context.getBean("bookJDBCTemplate");
	UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
	CategoryJDBCTemplate categoryJDBCTemplate = (CategoryJDBCTemplate)context.getBean("categoryJDBCTemplate");
//	optionsJDBCTemplate optionsJDBCTemplate = (optionsJDBCTemplate)context.getBean("optionsJDBCTemplate");

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView indexGET(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") != null) { return new ModelAndView("redirect:home"); }
		ModelAndView mv = new ModelAndView("login");
		String loginid = "";
		String password = "";
		mv.addObject("status", false);
		mv.addObject("loginid", loginid);
		mv.addObject("password", password);
		mv.addObject("menulinks",ml.getLinks(request));
		mv.addObject("quicklinks",ql.getLinks(request));
		mv.addObject("msg", "");
		return mv;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "loginid", required = false, defaultValue = "") String loginid,@RequestParam(value = "password", required = false, defaultValue = "") String password, HttpServletRequest request) {
		if(request.getSession().getAttribute("user") != null) { return new ModelAndView("redirect:home");}
		//Checking username and Password Combination
		Response resp = (Response)userJDBCTemplate.checkPassword(loginid, password);
		if(resp.status == true) {
			User user = (User)userJDBCTemplate.getUser(loginid);
			request.getSession().setAttribute("user",user.getUsername());
			request.getSession().setAttribute("usertype",user.getUsertype());
			return new ModelAndView("redirect:home");
		}
		else {
			//In case of Incorrect Password
			ModelAndView mv = new ModelAndView("login");
			mv.addObject("status", false);
			mv.addObject("loginid", loginid);
			mv.addObject("menulinks",ml.getLinks(request));
			mv.addObject("quicklinks",ql.getLinks(request));
			mv.addObject("password", password);
			mv.addObject("msg", resp.message);
			return mv;
		}
	}
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginGET(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") != null) { return new ModelAndView("redirect:home"); }
		ModelAndView mv = new ModelAndView("login");
		String loginid = "";
		String password = "";
		mv.addObject("status", false);
		mv.addObject("loginid", loginid);
		mv.addObject("password", password);
		mv.addObject("menulinks",ml.getLinks(request));
		mv.addObject("quicklinks",ql.getLinks(request));
		mv.addObject("msg", "");
		return mv;
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {

		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("usertype");
		return new ModelAndView("redirect:login");
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUser(HttpServletRequest request,
			@RequestParam(value = "username", required = false, defaultValue = "") String username,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "contact", required = false, defaultValue = "") String contact,
			@RequestParam(value = "address", required = false, defaultValue = "") String address,
			@RequestParam(value = "password", required = false, defaultValue = "") String password) {
		if(request.getSession().getAttribute("usertype") != null){ return new ModelAndView("redirect:home"); }
		if((name.length() == 0 || name.length() >= 50) || 
		   (username.length() < 2 || username.length() >= 40) || 
		   (contact.length() == 0 || contact.length() > 15) || 
		   address.length() == 0 || password.length() < 6) {
			//print errors on the form
			ModelAndView mv = new ModelAndView("register");
			mv.addObject("status", false);
			mv.addObject("menulinks",ml.getLinks(request));
			mv.addObject("quicklinks",ql.getLinks(request));
			mv.addObject("errorMessage","There are errors on your form. Check for size constraints.");
			mv.addObject("name", name);
			mv.addObject("username", username);
			mv.addObject("contact", contact);
			mv.addObject("address", address);
			mv.addObject("password", password);
			return mv;
		}
		Response resp = (Response)userJDBCTemplate.create(username, name, contact, address, password);

		ModelAndView mv = new ModelAndView("register");
		mv.addObject("status", resp.status);
		mv.addObject("menulinks",ml.getLinks(request));
		mv.addObject("quicklinks",ql.getLinks(request));
		if(resp.status == false) {
			mv.addObject("errorMessage",resp.message);
			mv.addObject("name", name);
			mv.addObject("username", username);
			mv.addObject("contact", contact);
			mv.addObject("address", address);
			mv.addObject("password", password);
		}
		else {
			mv.addObject("errorMessage",resp.message);
			mv.addObject("name", "");
			mv.addObject("username", "");
			mv.addObject("contact", "");
			mv.addObject("address", "");
			mv.addObject("password", "");
		}
		return mv;
	}
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registerUser(HttpServletRequest request) {
		if(request.getSession().getAttribute("usertype") != null){ return new ModelAndView("redirect:home"); }
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("status", false);
		mv.addObject("menulinks",ml.getLinks(request));
		mv.addObject("quicklinks",ql.getLinks(request));
		mv.addObject("errorMessage", "");
		mv.addObject("name", "");
		mv.addObject("username", "");
		mv.addObject("contact", "");
		mv.addObject("address", "");
		mv.addObject("password", "");
		return mv;
	}
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public ModelAndView dashboard(HttpServletRequest request) {
		if(request.getSession().getAttribute("usertype") == null){ return new ModelAndView("redirect:login"); }
		ModelAndView mv = new ModelAndView("dashboard");
		mv.addObject("menulinks",ml.getLinks(request));
		mv.addObject("quicklinks",ql.getLinks(request));
		String counter_username = (String) request.getSession().getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user);
		return mv;
	}
	@RequestMapping(value="/contacts", method=RequestMethod.GET)
	public ModelAndView contacts(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("contacts");
		mv.addObject("menulinks",ml.getLinks(request));
		mv.addObject("quicklinks",ql.getLinks(request));
		return mv;
	}
	@RequestMapping(value="/update_details", method=RequestMethod.GET)
	public ModelAndView updateDetails(HttpServletRequest request) {
		if (request.getSession().getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("update_user_details") */){ return new ModelAndView("redirect:login"); }
		ModelAndView mv = new ModelAndView("update_details");
		mv.addObject("menulinks",ml.getLinks(request));
		mv.addObject("quicklinks",ql.getLinks(request));
		String counter_username = (String) request.getSession().getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user);
		return mv;
	}
	@RequestMapping(value="/update_details", method=RequestMethod.POST)
	public ModelAndView updateUserDetails(HttpServletRequest request,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "contact", required = false, defaultValue = "") String contact,
			@RequestParam(value = "address", required = false, defaultValue = "") String address,
			@RequestParam(value = "old_password", required = false, defaultValue = "") String new_password,
			@RequestParam(value = "new_password", required = false, defaultValue = "") String old_password) {
		if (request.getSession().getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("update_user_details") */){ return new ModelAndView("redirect:login"); }
		ModelAndView mv = new ModelAndView("update_details");
		mv.addObject("menulinks",ml.getLinks(request));
		mv.addObject("quicklinks",ql.getLinks(request));
		String counter_username = (String) request.getSession().getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		Response resp;
		if(name.length() != 0 && contact.length() != 0 && address.length() != 0) { resp = userJDBCTemplate.update(user.getUsername(),name,contact,address);mv.addObject("errorMessage1", resp.message); }
		else if(old_password.length() > 6 && new_password.length() > 6) { resp = userJDBCTemplate.changePassword(user.getUsername(),old_password,new_password);mv.addObject("errorMessage2", resp.message); }
		else { return new ModelAndView("redirect:login"); }
		mv.addObject("status", resp.status);
		mv.addObject("user", user);
		return mv;
	}
	
	@RequestMapping(value = "/home", method=RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		if(request.getSession().getAttribute("usertype") == null){ return new ModelAndView("redirect:login"); }
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("menulinks",ml.getLinks(request));
		mv.addObject("quicklinks",ql.getLinks(request));
		String counter_username = (String) request.getSession().getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user);
		List<Category> listCategory = categoryJDBCTemplate.categoryList();
		mv.addObject("listCategory",listCategory);
		
		if(request.getParameter("category")!=null)
		{
			int category_id = Integer.parseInt(request.getParameter("category"));
			request.getSession().setAttribute("category_id", category_id);
			mv.addObject("category", categoryJDBCTemplate.getCategoryById(category_id));
			List<Component> listComponent = categoryJDBCTemplate.componentList(Integer.parseInt(request.getParameter("category")));
			mv.addObject("listComponent",listComponent);
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/construct_page", method=RequestMethod.GET)
	public ModelAndView constructPageGet(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView("constructed_page");
		String counter_username = (String) session.getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user);
		int id = (int)session.getAttribute("category_id");
		Category cat = categoryJDBCTemplate.getCategoryById(id);
		
		List<String> list = (List<String>)session.getAttribute("components");
		session.setAttribute("category_name", cat.getName());
		
		if(id== 1) {
			mv = showBooks(request,"");
			
		}
		
		mv.addObject("components",list);
		mv.addObject("category",cat.getName());
		return mv;
	}
	
	@RequestMapping(value = "/construct_page", method=RequestMethod.POST)
	public ModelAndView constructPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView("constructed_page");
		String counter_username = (String) session.getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user);
		int id = (int)session.getAttribute("category_id");
		Category cat = categoryJDBCTemplate.getCategoryById(id);
		String[] components = request.getParameterValues("components");
		List<String> list = null;
		if(components!=null) { list = Arrays.asList(components);}
		
		session.setAttribute("components", list);
		session.setAttribute("category_name", cat.getName());
		
		if(id== 1) {
			mv = showBooks(request,"");
			
		}
		
		mv.addObject("components",list);
		mv.addObject("category",cat.getName());
		return mv;
	}
	
	@RequestMapping(value="/books", method=RequestMethod.GET)
	public ModelAndView showBooks(HttpServletRequest request,@RequestParam(value = "search", required = false, defaultValue = "") String search) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		String usertype = (String) session.getAttribute("usertype");
		List<Book> Books;
		if(search != "") {Books = bookJDBCTemplate.searchBook(search);}
		else{Books = bookJDBCTemplate.listBooks();}
		ModelAndView mv = new ModelAndView("constructed_page");
		mv.addObject("status", true);
//		mv.addObject("check_case", optionsJDBCTemplate);
		mv.addObject("books", Books);
		
		/*
		 * if(Books.size() == 0) { Response resp = new
		 * Response(false,"No Books Found!"); mv.addObject("response", resp); }
		 */
		
		//Adding Components to page
		List<String> list = (List<String>)session.getAttribute("components");
		String catname = (String) session.getAttribute("category_name");
		
		mv.addObject("components",list);
		mv.addObject("category",catname);
		
		if(usertype != null) { User user = userJDBCTemplate.getUser(username); mv.addObject("user", user.getName());mv.addObject("usertype", usertype); }
		else { mv.addObject("usertype", 0);mv.addObject("user", "Guest");mv.addObject("usertype", "Guest"); }
		if(search != "") {mv.addObject("search", search);}
		else {mv.addObject("search", "");}
		return mv;
	}
	
	@RequestMapping(value="/issue-return", method=RequestMethod.GET)
	public ModelAndView issueReturnBook(HttpServletRequest request) {
		if (request.getSession()
				.getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("issue_return_books") */){ return new ModelAndView("redirect:login"); }
//		String usertype = (String) request.getSession().getAttribute("usertype");
		/*
		 * if(!usertype.matches("admin")) { return new ModelAndView("redirect:login"); }
		 */
		List<User> users = userJDBCTemplate.listUsers();
		List<Book> books = bookJDBCTemplate.listBooks();
		ModelAndView mv = new ModelAndView("IssueReturnPage");
		mv.addObject("status", false);
		String catname = (String) request.getSession().getAttribute("category_name");
		mv.addObject("category",catname);
		
		String username = (String) request.getSession().getAttribute("user");
		User user = userJDBCTemplate.getUser(username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user.getName());
		mv.addObject("users", users);
		mv.addObject("books", books);
		return mv;
	}
	
	@RequestMapping(value="/issue-return", method=RequestMethod.POST)
	public ModelAndView issueReturnForm(HttpServletRequest request,
			@RequestParam(value = "username", required = false, defaultValue = "") String username,
			@RequestParam(value = "bookid", required = false, defaultValue = "0") Integer bookid) {
		if (request.getSession()
				.getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("issue_return_books") */){ return new ModelAndView("redirect:login"); }
//		String usertype = (String) request.getSession().getAttribute("usertype");
		/*
		 * if(!usertype.matches("admin")) { return new ModelAndView("redirect:login"); }
		 */
		
		HttpSession session = request.getSession();
		List<User> users = userJDBCTemplate.listUsers();
		List<Book> books = bookJDBCTemplate.listBooks();
		ModelAndView mv = new ModelAndView("IssueReturnPage");
		//Adding Components to page

		String catname = (String) session.getAttribute("category_name");
		mv.addObject("category",catname);
		
		String counter_username = (String) session.getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		Response resp = bookJDBCTemplate.toggleIssueReturn(bookid, username, user.getUsername());
		mv.addObject("status", resp.status);
		mv.addObject("errorMessage", resp.message);
		mv.addObject("user", user.getName());
		mv.addObject("users", users);
		mv.addObject("books", books);
		return mv;
	}
	
	@RequestMapping(value="/add-book", method=RequestMethod.POST)
	public ModelAndView addBooks(HttpServletRequest request,@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "author", required = false, defaultValue = "") String author,@RequestParam(value = "copies", required = false, defaultValue = "0") Integer copies) {
		HttpSession session = request.getSession();
		if (session
				.getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("update_booklist") */){ return new ModelAndView("redirect:books"); }
//		String usertype = (String) session.getAttribute("usertype");
		if((name.length() == 0 || name.length() >= 50) || (author.length() == 0 || author.length() >= 50) || copies < 0) { return new ModelAndView("redirect:books"); }
//		if(usertype.matches("admin")) { bookJDBCTemplate.create(name, author, copies, copies); }
		Response resp = bookJDBCTemplate.create(name, author, copies, copies);
		return new ModelAndView("redirect:books");
	}
	
	@RequestMapping(value="/edit-book", method=RequestMethod.POST)
	public ModelAndView addBooks(HttpServletRequest request,@RequestParam(value = "bookid", required = false, defaultValue = "-1") Integer bookid,
			@RequestParam(value = "total", required = false, defaultValue = "0") Integer total,@RequestParam(value = "rem", required = false, defaultValue = "0") Integer rem) {
		HttpSession session = request.getSession();
		if (session
				.getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("update_booklist") */){ return new ModelAndView("redirect:books"); }
//		String usertype = (String) session.getAttribute("usertype");
		if(bookid == -1 || total < 0 || rem < 0) { return new ModelAndView("redirect:books"); }
//		if(usertype.matches("admin")) { bookJDBCTemplate.update(bookid, total, rem); }
		Response resp = bookJDBCTemplate.update(bookid, total, rem); 
		return new ModelAndView("redirect:books");
	}
	
	@RequestMapping(value="/about", method=RequestMethod.GET)
	public ModelAndView about_us(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("about_us");
		mv.addObject("menulinks",ml.getLinks(request));
		mv.addObject("quicklinks",ql.getLinks(request));
		return mv;
	}
}
































