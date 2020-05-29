package com.mini.cbse.components;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface Component {
	void doGetAction(HttpServletRequest request, Object parameters);
	void doPostAction(HttpServletRequest request, Object parameters);
	String getViewName();
	Map<String,Object> getModelMap();
}
