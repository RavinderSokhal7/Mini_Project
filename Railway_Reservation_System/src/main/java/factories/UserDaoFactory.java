package factories;

import interfaces.UserDao;
import models.UserDaoImpl;

public class UserDaoFactory {
	public static UserDao getInstance() {
		return new UserDaoImpl();
	}
}
