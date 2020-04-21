package com.mini.cbse.Category;

public class Component {
	private int id;
	private int category_id;
    private String name;
    private String detail;
	
	public Component(int id, int category_id, String name, String detail) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.name = name;
		this.detail = detail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

    
}
