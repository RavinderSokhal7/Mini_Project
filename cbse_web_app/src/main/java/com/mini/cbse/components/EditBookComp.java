package com.mini.cbse.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.mini.cbse.Response;
import com.mini.cbse.Book.Book;
import com.mini.cbse.Book.BookJDBCTemplate;
import com.mini.cbse.Category.CategoryJDBCTemplate;
import com.mini.cbse.Train.TrainJDBCTemplate;
import com.mini.cbse.User.User;
import com.mini.cbse.User.UserJDBCTemplate;

public class EditBookComp implements Component {
	
	private String viewName;
	private Map<String,Object> modelMap;
	ApplicationContext context = new ClassPathXmlApplicationContext("DataSource.xml");
	BookJDBCTemplate bookJDBCTemplate = (BookJDBCTemplate)context.getBean("bookJDBCTemplate");
	UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
	CategoryJDBCTemplate categoryJDBCTemplate = (CategoryJDBCTemplate)context.getBean("categoryJDBCTemplate");
	TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate)context.getBean("trainJDBCTemplate");
	
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public void setModelMap(Map<String, Object> modelMap) {
		this.modelMap = modelMap;
	}

	@Override
	public void doGetAction(HttpServletRequest request, Object parameters) {
		if (request.getSession()
				.getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("issue_return_books") */){ this.setViewName("redirect:login"); return;}
//		String usertype = (String) request.getSession().getAttribute("usertype");
		/*
		 * if(!usertype.matches("admin")) { return new ModelAndView("redirect:login"); }
		 */
		String username = (String) request.getSession().getAttribute("user");
		User user = userJDBCTemplate.getUser(username);
		if(user == null) { this.setViewName("redirect:logout"); return; }
		
		List<Book> books = bookJDBCTemplate.listBooks();
		this.setViewName("EditBookPage");
		Map<String,Object> modelMap = new HashMap<>();
		
		modelMap.put("status", false);
		modelMap.put("books", books);
		modelMap.put("user", user.getName());
		this.setModelMap(modelMap);
		return;
	}

	@Override
	public void doPostAction(HttpServletRequest request, Object parameters) {
		HttpSession session = request.getSession();
		HashMap<String,Object> params = (HashMap<String,Object>) parameters;
		
		if (session.getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("update_booklist") */){ this.setViewName("redirect:login"); return;}

		int total = (int)params.get("total");
		int rem = (int)params.get("rem");
		int bookid = (int)params.get("bookid");
//		String usertype = (String) session.getAttribute("usertype");
		if(bookid == -1 || total < 0 || rem < 0) { this.setViewName("redirect:EditBookPage"); return;}
//		if(usertype.matches("admin")) { bookJDBCTemplate.update(bookid, total, rem); }
		
		this.setViewName("EditBookPage");
		Response resp = bookJDBCTemplate.update(bookid, total, rem);
		List<Book> books = bookJDBCTemplate.listBooks();
		Map<String,Object> modelMap = new HashMap<>();
		modelMap.put("books", books);
		modelMap.put("status", resp.status);
		modelMap.put("errorMessage", resp.message);
		this.setModelMap(modelMap);
		
		return;
	}

	@Override
	public String getViewName() {
		return viewName;
	}

	@Override
	public Map<String, Object> getModelMap() {
		return modelMap;
	}

}
