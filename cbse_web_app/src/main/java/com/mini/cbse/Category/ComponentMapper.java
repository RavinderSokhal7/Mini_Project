package com.mini.cbse.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ComponentMapper implements RowMapper<Component> {

	@Override
	public Component mapRow(ResultSet rs, int rowNum) throws SQLException {
		Component com = new Component(  rs.getInt("id"),
										rs.getInt("category_id"),
										rs.getString("name"),
										rs.getString("detail")); 
		return com;
	}

}
