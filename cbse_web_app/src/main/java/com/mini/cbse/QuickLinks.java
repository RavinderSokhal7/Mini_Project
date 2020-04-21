package com.mini.cbse;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class QuickLinks {

	List<Link> userLinks;
	List<Link> guestLinks;
	
	public QuickLinks() {

		userLinks = new ArrayList<>();

		this.userLinks.add(new Link("dashboard","Dashboard"));
		this.userLinks.add(new Link("update_details","Update Details"));
		this.userLinks.add(new Link("logout","Logout"));

		guestLinks = new ArrayList<>();
		this.guestLinks.add(new Link("login","Login"));
		this.guestLinks.add(new Link("register","Register"));
	}
	public List<Link> getLinks(HttpServletRequest request){
		if(request.getSession().getAttribute("user") != null) {
//			String usertype = (String)request.getSession().getAttribute("usertype");
			/*
			 * if(usertype.matches("admin")) { return adminLinks; } else { return userLinks;
			 * }
			 */
			return userLinks;
		}
		return guestLinks;
	}
}