package com.mini.cbse.User;

import java.util.List;
import com.mini.cbse.Response;
import javax.sql.DataSource;

public interface UserDAO{
	public void setDataSource(DataSource ds);
	public Response checkIfExists(String username);
	public Response create(String username, String name, String contact, String address, String password);
	public User getUser(String username);
	public List<User> listUsers();
	public List<User> searchUser(String query);
	public Response delete(String username);
	public Response update(String new_username, String name, String contact, String address);
	public Response changePassword(String username, String old_password, String new_password);
	public Response checkPassword(String username, String password);
}