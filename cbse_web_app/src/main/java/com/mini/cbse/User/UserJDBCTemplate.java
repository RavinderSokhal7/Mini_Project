package com.mini.cbse.User;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mini.cbse.MD5;
import com.mini.cbse.Response;
import com.mini.cbse.Book.*;

public class UserJDBCTemplate implements UserDAO{
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public Response create(String username, String name, String contact, String address, String password) {
		username = username.toLowerCase();
		Response resp = (Response)checkIfExists(username);
		if(resp.status == true) { resp = new Response(false,"Username is already taken!"); return resp; }
		password = MD5.getMd5(password);
		String SQL = "INSERT into users (username, name, contact, address) values (?,?,?,?)";
		jdbcTemplateObject.update( SQL, username, name, contact, address);
		SQL = "INSERT into login (username, password) values (?,?)";
		jdbcTemplateObject.update( SQL, username, password);
		System.out.println("Added a new User: "+username+" - "+name+"\nDetails: "+contact+", "+address);
		resp = new Response(true,"User Successfully Created");
		return resp;
	}

	public User getUser(String username) {
		username = username.toLowerCase();
		String SQL = "SELECT * from users where username = ?";
		List<User> users = jdbcTemplateObject.query(SQL, new Object[]{username}, new UserMapper());
		if(users.size() == 0) { return null; }
		return users.get(0);
	}

	public List<User> listUsers() {
		String SQL = "SELECT * from users where usertype = 'user'";
		List <User> users = jdbcTemplateObject.query(SQL, new UserMapper());
		return users;
	}

	public List<User> searchUser(String query) {
		query = '%'+query+'%';
		String SQL = "SELECT * from users where UPPER(name) LIKE UPPER(?) or UPPER(address) LIKE UPPER(?) or UPPER(contact) LIKE UPPER(?) or UPPER(username) LIKE UPPER(?)";
		List <User> users = jdbcTemplateObject.query(SQL, new Object[] {query,query}, new UserMapper());
		return users;
	}

	
	public Response delete(String username) {
		username = username.toLowerCase();
		String SQL = "DELETE from users where username = ?";
		jdbcTemplateObject.update(SQL, username);
		System.out.println("Deleted User with username = " + username);
		Response resp = new Response(true,"User Successfully Deleted");
		return resp;
	}

	public Response update(String username, String name, String contact, String address) {
		username = username.toLowerCase();
		Response resp = checkIfExists(username);
		if(resp.status == true) {
			String SQL = "UPDATE users set name = ?, contact = ?, address = ? where username = ?";
			jdbcTemplateObject.update(SQL, name, contact, address,username);
			System.out.println("Updated details of User with username = " + username);
			resp = new Response(true,"User Successfully Updated");
			return resp;
		}
		else {
			resp = new Response(false,resp.message);
			return resp;
		}
	}

	public Response changePassword(String username, String old_password, String new_password) {
		username = username.toLowerCase();
		Response resp = this.checkPassword(username,old_password);
		if(resp.status == true) {
			new_password = MD5.getMd5(new_password);
			String SQL = "UPDATE login set password = ? where username = ?";
			jdbcTemplateObject.update(SQL, new_password,username);
			System.out.println("Updated details of User with username = " + username);
			resp = new Response(true,"User Password Successfully Changed");
			return resp;
		}
		else {
			resp = new Response(false,resp.message);
			return resp;
		}
	}

	public Response checkPassword(String username, String password) {
		username = username.toLowerCase();
		password = MD5.getMd5(password);
		String SQL = "SELECT * from login where username = ? and password = ?";
		List<User> users = jdbcTemplateObject.query(SQL, new Object[]{username,password}, new LoginMapper());
		User user;
		if(users.size() == 0) { user = null; }
		else { user = users.get(0); }
		if(user != null) {
			SQL = "SELECT * from users where username = ?";
			users = jdbcTemplateObject.query(SQL, new Object[]{username}, new UserMapper());
			if(users.size() == 0) { user = null; }
			else { user = users.get(0); }
			Response resp = new Response(true,"Welcome "+user.getName());
			return resp;
		}
		else {
			Response resp = new Response(false,"Wrong User and Password Combination");
			return resp;
		}
	}

	public Response checkIfExists(String username) {
		username = username.toLowerCase();
		String SQL = "SELECT * from users where username = ?";
		List<User> users = jdbcTemplateObject.query(SQL, new Object[]{username}, new UserMapper());
		if(users.size() == 1) {
			Response resp = new Response(true,"User Exists");
			return resp;
		}
		else {
			Response resp = new Response(false,"User Does Not Exists");
			return resp;
		}
	}
	public List<Book> currentIssuedBooks(String username){
		username = username.toLowerCase();
		Response resp = (Response)checkIfExists(username);
		List<Book> books;
		if(resp.status == true) {
			String SQL = "SELECT * from issue_logs,books where username = ? and status = 'issued' and books.id = issue_logs.id";
			books = jdbcTemplateObject.query(SQL, new Object[]{username}, new BookMapper());
		}
		else {
			books = null;
		}
		return books;
	}
}