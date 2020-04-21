package com.mini.cbse.Category;

import java.util.List;

import javax.sql.DataSource;

public interface CategoryDAO {
	public void setDataSource(DataSource ds);
	public Category getCategoryById(int id);
	public List<Category> categoryList();
	public List<Component> componentList(int cat_id);
}
