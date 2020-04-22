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

import com.mini.cbse.Response;
import com.mini.cbse.Book.Book;
import com.mini.cbse.Book.BookJDBCTemplate;
import com.mini.cbse.Category.CategoryJDBCTemplate;
import com.mini.cbse.Train.TrainJDBCTemplate;
import com.mini.cbse.User.User;
import com.mini.cbse.User.UserJDBCTemplate;

@Controller
public class LibraryController {
	ApplicationContext context = new ClassPathXmlApplicationContext("DataSource.xml");
	BookJDBCTemplate bookJDBCTemplate = (BookJDBCTemplate)context.getBean("bookJDBCTemplate");
	UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
	CategoryJDBCTemplate categoryJDBCTemplate = (CategoryJDBCTemplate)context.getBean("categoryJDBCTemplate");
	TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate)context.getBean("trainJDBCTemplate");


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
	
	@RequestMapping(value="/add-book", method=RequestMethod.GET)
	public ModelAndView addBooksGet(HttpServletRequest request) {
		if (request.getSession()
				.getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("issue_return_books") */){ return new ModelAndView("redirect:login"); }
//		String usertype = (String) request.getSession().getAttribute("usertype");
		/*
		 * if(!usertype.matches("admin")) { return new ModelAndView("redirect:login"); }
		 */
		ModelAndView mv = new ModelAndView("AddBookPage");
		mv.addObject("status", false);
		String catname = (String) request.getSession().getAttribute("category_name");
		mv.addObject("category",catname);
		
		String username = (String) request.getSession().getAttribute("user");
		User user = userJDBCTemplate.getUser(username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user.getName());
		
		return mv;
	}
	@RequestMapping(value="/edit-book", method=RequestMethod.GET)
	public ModelAndView editBooksGet(HttpServletRequest request) {
		if (request.getSession()
				.getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("issue_return_books") */){ return new ModelAndView("redirect:login"); }
//		String usertype = (String) request.getSession().getAttribute("usertype");
		/*
		 * if(!usertype.matches("admin")) { return new ModelAndView("redirect:login"); }
		 */
		List<Book> books = bookJDBCTemplate.listBooks();
		ModelAndView mv = new ModelAndView("EditBookPage");
		mv.addObject("status", false);
		String catname = (String) request.getSession().getAttribute("category_name");
		mv.addObject("category",catname);
		mv.addObject("books", books);
		
		String username = (String) request.getSession().getAttribute("user");
		User user = userJDBCTemplate.getUser(username);
		if(user == null) { return new ModelAndView("redirect:logout"); }
		mv.addObject("user", user.getName());
		
		return mv;
	}
	
	@RequestMapping(value="/add-book", method=RequestMethod.POST)
	public ModelAndView addBooks(HttpServletRequest request,@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "author", required = false, defaultValue = "") String author,@RequestParam(value = "copies", required = false, defaultValue = "0") Integer copies) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView("AddBookPage");
		if (session
				.getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("update_booklist") */){ return new ModelAndView("redirect:login"); }
//		String usertype = (String) session.getAttribute("usertype");
		if((name.length() == 0 || name.length() >= 50) || (author.length() == 0 || author.length() >= 50) || copies < 0) { return new ModelAndView("redirect:AddBookPage"); }
//		if(usertype.matches("admin")) { bookJDBCTemplate.create(name, author, copies, copies); }

		String catname = (String) session.getAttribute("category_name");
		mv.addObject("category",catname);
		Response resp = bookJDBCTemplate.create(name, author, copies, copies);
		mv.addObject("status", resp.status);
		mv.addObject("errorMessage", resp.message);
		return mv;
	}
	
	@RequestMapping(value="/edit-book", method=RequestMethod.POST)
	public ModelAndView editBooks(HttpServletRequest request,@RequestParam(value = "bookid", required = false, defaultValue = "-1") Integer bookid,
			@RequestParam(value = "total", required = false, defaultValue = "0") Integer total,@RequestParam(value = "rem", required = false, defaultValue = "0") Integer rem) {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView("EditBookPage");
		if (session
				.getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("update_booklist") */){ return new ModelAndView("redirect:login"); }
//		String usertype = (String) session.getAttribute("usertype");
		if(bookid == -1 || total < 0 || rem < 0) { return new ModelAndView("redirect:EditBookPage"); }
//		if(usertype.matches("admin")) { bookJDBCTemplate.update(bookid, total, rem); }
		String catname = (String) session.getAttribute("category_name");
		mv.addObject("category",catname);
		Response resp = bookJDBCTemplate.update(bookid, total, rem);
		List<Book> books = bookJDBCTemplate.listBooks();
		mv.addObject("books", books);
		mv.addObject("status", resp.status);
		mv.addObject("errorMessage", resp.message);
		return mv;
	}
}
