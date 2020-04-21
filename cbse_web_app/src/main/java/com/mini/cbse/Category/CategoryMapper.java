package com.mini.cbse.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class CategoryMapper implements RowMapper<Category> {

	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		  Category cat = new Category(rs.getInt("category_id"),rs.getString("name"));
//	      cat.setId(rs.getInt("id"));
//	      cat.setName(rs.getString("name"));
		return cat;
	}

}
