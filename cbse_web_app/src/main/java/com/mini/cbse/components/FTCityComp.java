package com.mini.cbse.components;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class FTCityComp implements Component {
	
	private String viewName;
	private Map<String,Object> modelMap;
	
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public void setModelMap(Map<String, Object> modelMap) {
		this.modelMap = modelMap;
	}

	@Override
	public void doGetAction(HttpServletRequest request, Object parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doPostAction(HttpServletRequest request, Object parameters) {
		HttpSession session = request.getSession();
		this.setViewName("constructed_page");
		String fromCity = request.getParameter("fromCity");
		String toCity = request.getParameter("toCity");
		Map<String,Object> modelMap = new HashMap<>();
		if(fromCity!=null && toCity!=null) {
			modelMap.put("fromCity", fromCity);
			modelMap.put("toCity", toCity);
//			System.out.println(fromCity+toCity);
			session.setAttribute("fromCity", fromCity);
			session.setAttribute("toCity", toCity);
		}
		else if(fromCity!=null) {
			modelMap.put("fromCity", fromCity);
			session.setAttribute("fromCity", fromCity);
//			System.out.println("to NULL");
		}
		else if(toCity!=null){
			modelMap.put("toCity", toCity);
			session.setAttribute("toCity", toCity);
//			System.out.println("from Null");
		}
		else {
//			System.out.println("Both NULL");
		}

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
