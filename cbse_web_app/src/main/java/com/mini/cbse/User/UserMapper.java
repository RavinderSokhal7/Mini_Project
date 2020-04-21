package com.mini.cbse.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {
   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      User user = new User();
      user.setUsername(rs.getString("username"));
      user.setName(rs.getString("name"));
      user.setContact(rs.getString("contact"));
      user.setAddress(rs.getString("address"));
      user.setUsertype(rs.getString("usertype"));
      return user;
   }
}