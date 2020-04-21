package com.mini.cbse.Category;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class CategoryJDBCTemplate implements CategoryDAO {
	private JdbcTemplate jdbcTemplateObject;
	// QUERIES
	private String SELECT_ALL_CATEGORIES = "SELECT * from category";
	private String SELECT_ALL_COMPONENTS = "Select * from component where category_id = ?";
	private String SELECT_CATEGORY_BY_ID = "Select * from category where category_id = ?";
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Category> categoryList() {
		List <Category> list = jdbcTemplateObject.query(SELECT_ALL_CATEGORIES, new CategoryMapper());
		return list;
	}

	@Override
	public List<Component> componentList(int cat_id) {
		List <Component> list = jdbcTemplateObject.query(SELECT_ALL_COMPONENTS,new Object[] {cat_id}, new ComponentMapper());
		return list;
	}

	@Override
	public Category getCategoryById(int id) {
		Category cat=null;
		List <Category> list = jdbcTemplateObject.query(SELECT_CATEGORY_BY_ID,new Object[] {id}, new CategoryMapper());
		cat = list.get(0);
		return cat;
	}

}
