package com.mini.cbse.Book;

public class Book{
	private Integer id;
	private String name;
	private String author;
	private Integer total;
	private Integer rem;

	public void setID(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public void setRemaining(Integer rem) {
		this.rem = rem;
	}
	public Integer getID() {return this.id;}
	public String getName() {return this.name;}
	public String getAuthor() {return this.author;}
	public Integer getTotal() {return this.total;}
	public Integer getRemaining() {return this.rem;}
}