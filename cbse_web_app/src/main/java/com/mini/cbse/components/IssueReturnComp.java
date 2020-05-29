package com.mini.cbse.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mini.cbse.Response;
import com.mini.cbse.Book.Book;
import com.mini.cbse.Book.BookJDBCTemplate;
import com.mini.cbse.User.User;
import com.mini.cbse.User.UserJDBCTemplate;

public class IssueReturnComp implements Component {

	private String viewName;
	private Map<String,Object> modelMap;
	ApplicationContext context = new ClassPathXmlApplicationContext("DataSource.xml");
	BookJDBCTemplate bookJDBCTemplate = (BookJDBCTemplate)context.getBean("bookJDBCTemplate");
	UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
	
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
		if(user == null) { this.setViewName("redirect:logout"); return;}
		Map<String,Object> modelMap = new HashMap<>();
		List<User> users = userJDBCTemplate.listUsers();
		List<Book> books = bookJDBCTemplate.listBooks();
		this.setViewName("IssueReturnPage");
		modelMap.put("status", false);		
		modelMap.put("user", user.getName());
		modelMap.put("users", users);
		modelMap.put("books", books);
		this.setModelMap(modelMap);
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doPostAction(HttpServletRequest request, Object parameters) {
		if (request.getSession().getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("issue_return_books") */){ this.setViewName("redirect:login"); return;}
//		String usertype = (String) request.getSession().getAttribute("usertype");
		/*
		 * if(!usertype.matches("admin")) { return new ModelAndView("redirect:login"); }
		 */
		
		String counter_username = (String) request.getSession().getAttribute("user");
		User user = userJDBCTemplate.getUser(counter_username);
		if(user == null) { this.setViewName("redirect:logout"); return;}
		
		List<User> users = userJDBCTemplate.listUsers();
		List<Book> books = bookJDBCTemplate.listBooks();
		this.setViewName("IssueReturnPage");
		//Adding Components to page
		HashMap<String,Object> params = (HashMap<String,Object>) parameters;
		Map<String,Object> modelMap = new HashMap<>();
		int bookid = (int)params.get("bookid");
		String username = params.get("username").toString();
		Response resp = bookJDBCTemplate.toggleIssueReturn(bookid, username, user.getUsername());
		modelMap.put("status", resp.status);
		modelMap.put("errorMessage", resp.message);
		modelMap.put("user", user.getName());
		modelMap.put("users", users);
		modelMap.put("books", books);
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
