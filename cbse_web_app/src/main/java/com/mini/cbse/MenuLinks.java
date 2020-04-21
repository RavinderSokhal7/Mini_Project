package com.mini.cbse;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
public class MenuLinks {

	private List<Link> userLinks;
	private List<Link> guestLinks;
	
	public MenuLinks() {

		userLinks = new ArrayList<>();
		this.userLinks.add(new Link("home","Home"));
		this.userLinks.add(new Link("dashboard","Dashboard"));
		this.userLinks.add(new Link("update_details","Update Details"));
		this.userLinks.add(new Link("logout","Logout"));
		this.userLinks.add(new Link("about","About Us"));
		this.userLinks.add(new Link("contacts","Contact Us"));

		guestLinks = new ArrayList<>();
		this.guestLinks.add(new Link("login","Login"));
		this.guestLinks.add(new Link("register","Register"));
		this.guestLinks.add(new Link("about","About Us"));
		this.guestLinks.add(new Link("contacts","Contact Us"));
	}
	public List<Link> getLinks(HttpServletRequest request){
		if(request.getSession().getAttribute("user") != null) {
			//String usertype = (String)request.getSession().getAttribute("usertype");
			/*
			 * if(usertype.matches("admin")) { return adminLinks; } else { return userLinks;
			 * }
			 */
			return userLinks;
		}
		return guestLinks;
	}
}