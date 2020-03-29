package interfaces;

import java.sql.SQLException;

import models.UserBO;

public interface UserDao {
	public boolean registerUser(UserBO u) throws SQLException, ClassNotFoundException;
	public boolean checkUserForLogin(UserBO u)throws SQLException, ClassNotFoundException;
	public UserBO getUserDetails(String username)throws SQLException, ClassNotFoundException;
}
