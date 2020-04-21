package com.mini.cbse.User;

public class User{
	private String username;
	private String name;
	private String contact;
	private String address;
	private String usertype;
	private String password;

	public void setUsername(String username) {this.username = username;}
	public void setName(String name) {this.name = name;}
	public void setContact(String contact) {this.contact = contact;}
	public void setAddress(String address) {this.address = address;}
	public void setUsertype(String usertype) {this.usertype = usertype;}
	public void setPassword(String password) {this.password = password;}
	public String getUsername() {return this.username;}
	public String getName() {return this.name;}
	public String getContact() {return this.contact;}
	public String getAddress() {return this.address;}
	public String getUsertype() {return this.usertype;}
	public String getPassword() {return this.password;}
}