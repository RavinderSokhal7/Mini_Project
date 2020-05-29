package com.mini.cbse.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.mini.cbse.Book.Book;
import com.mini.cbse.Book.BookJDBCTemplate;
import com.mini.cbse.Category.CategoryJDBCTemplate;
import com.mini.cbse.Train.TrainJDBCTemplate;
import com.mini.cbse.User.User;
import com.mini.cbse.User.UserJDBCTemplate;

public class ShowBooksComp implements Component {
	
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
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		String usertype = (String) session.getAttribute("usertype");
		List<Book> Books;
		HashMap<String,Object> params = (HashMap<String,Object>) parameters;
		Map<String,Object> modelMap = new HashMap<>();
		String search = params.get("search").toString();
		if(search != "") {Books = bookJDBCTemplate.searchBook(search);}
		else{Books = bookJDBCTemplate.listBooks();}
		this.setViewName("constructed_page");
		modelMap.put("status", true);
//		mv.addObject("check_case", optionsJDBCTemplate);
		modelMap.put("books", Books);
		
		/*
		 * if(Books.size() == 0) { Response resp = new
		 * Response(false,"No Books Found!"); mv.addObject("response", resp); }
		 */
		
		//Adding Components to page
		List<String> list = (List<String>)session.getAttribute("components");
		String catname = (String) session.getAttribute("category_name");
		
		modelMap.put("components",list);
		modelMap.put("category",catname);
		
		if(usertype != null) { User user = userJDBCTemplate.getUser(username); modelMap.put("user", user.getName());modelMap.put("usertype", usertype); }
		else { modelMap.put("usertype", 0);modelMap.put("user", "Guest");modelMap.put("usertype", "Guest"); }
		if(search != "") {modelMap.put("search", search);}
		else {modelMap.put("search", "");}

		this.setModelMap(modelMap);
		return;
	}

	@Override
	public void doPostAction(HttpServletRequest request, Object parameters) {
		// TODO Auto-generated method stub
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
