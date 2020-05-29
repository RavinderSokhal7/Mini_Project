package com.mini.cbse.components;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mini.cbse.Response;
import com.mini.cbse.Book.BookJDBCTemplate;
import com.mini.cbse.User.User;
import com.mini.cbse.User.UserJDBCTemplate;

public class AddBookComp implements Component {
	
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
		if(user == null) { this.setViewName("redirect:logout"); return; }
		
		this.setViewName("AddBookPage");
		
		Map<String,Object> modelMap = new HashMap<>();

		modelMap.put("user", user.getName());
		Boolean status = new Boolean( false );
		modelMap.put("status", status);
		this.setModelMap(modelMap);
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doPostAction(HttpServletRequest request, Object parameters) {
		HttpSession session = request.getSession();
		HashMap<String,Object> params = (HashMap<String,Object>) parameters;
		if (session.getAttribute("usertype") == null /* || !optionsJDBCTemplate.checkStatus("update_booklist") */){ this.setViewName("redirect:login"); return; }
//		String usertype = (String) session.getAttribute("usertype");
		String name = params.get("name").toString();
		String author = params.get("author").toString();
		int copies = (int)params.get("copies");
		if((name.length() == 0 || name.length() >= 50) || (author.length() == 0 || author.length() >= 50) || copies < 0) { this.setViewName("redirect:AddBookPage"); return;}
//		if(usertype.matches("admin")) { bookJDBCTemplate.create(name, author, copies, copies); }
		Map<String,Object> modelMap = new HashMap<>();
		
		this.setViewName("AddBookPage");
//		String catname = (String) session.getAttribute("category_name");
//		modelMap.put("category",catname);
		Response resp = bookJDBCTemplate.create(name, author, copies, copies);
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
