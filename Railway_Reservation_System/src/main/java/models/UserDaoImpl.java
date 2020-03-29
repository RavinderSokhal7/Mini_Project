package models;

import java.sql.*;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import interfaces.UserDao;

@Component
@Scope("singleton")
@Primary
public class UserDaoImpl implements UserDao {
//Variables
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pst;
	private String dburl = "jdbc:mysql://localhost:3306/logindata";
	private String uname = "root";
	private String pass = "password";
//	private UserBO user;
//QUERY PART
	private String INSERT_QUERY = "INSERT INTO `logindata`.`users` (`username`, `password`, `firstname`, `lastname`, `address`, `contactno`) VALUES (?,?,?,?,?,?);";
	private String SELECT_FOR_LOGIN = "SELECT `username` from users where username=? and password=?;";
	private String SELECT_FOR_REGISTER = "SELECT `username` from users where username=?;";
	private String SELECT_FOR_GETUSERDETAILS = "SELECT * from users where username=?;";
	
	@Override
	public boolean registerUser(UserBO u) throws SQLException, ClassNotFoundException {
//		user = u;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dburl, uname, pass);
		pst = con.prepareStatement(SELECT_FOR_REGISTER);
		pst.setString(1,u.getUsername());
		rs = pst.executeQuery();
		if(rs.next()) {
			return false;
		}
		pst.close();
		pst = con.prepareStatement(INSERT_QUERY);
		pst.setString(1,u.getUsername());
		pst.setString(2, u.getPassword());
		pst.setString(3,u.getFirstname());
		pst.setString(4, u.getLastname());
		pst.setString(5,u.getAddress());
		pst.setString(6, u.getContactno());
		pst.executeUpdate();
		pst.close();
		con.close();
		return true;
	}

	@Override
	public boolean checkUserForLogin(UserBO u) throws SQLException, ClassNotFoundException {
//		String uname = null;
//		String pass = null;
//		int flag =0;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dburl, uname, pass);
		pst = con.prepareStatement(SELECT_FOR_LOGIN);
		pst.setString(1,u.getUsername());
		pst.setString(2, u.getPassword());
		rs = pst.executeQuery();
		if(rs.next()) {
			rs.close();
			pst.close();
			con.close();
				return true;
		}
		
		if(pst!=null)
			pst.close();
		if(con!=null)
			con.close();
		return false;
	}

	@Override
	public UserBO getUserDetails(String username) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dburl, uname, pass);
		pst = con.prepareStatement(SELECT_FOR_GETUSERDETAILS);
		pst.setString(1,username);
		rs = pst.executeQuery();
		UserBO u = new UserBO();
		if(rs.next()) {
			u.setFirstname(rs.getString("firstname"));
			u.setLastname(rs.getString("lastname"));
			u.setUsername(rs.getString("username"));
			u.setAddress(rs.getString("address"));
			u.setContactno(rs.getString("contactno"));
			u.setPassword(rs.getString("password"));
		}
		else {
			return null;
		}
		pst.close();
		con.close();
		return u;
	}

}
