package com.mini.cbse.Book;

import java.util.List;
import com.mini.cbse.Response;
import javax.sql.DataSource;

public interface BookDAO{
	public void setDataSource(DataSource ds);
	public Response checkIfExists(Integer id);
	public Response create(String name, String author, Integer total, Integer rem);
	public Book getBook(Integer bookid);
	public List<Book> listBooks();
	public List<Book> searchBook(String query);
	public Response delete(Integer id);
	public Response update(Integer id, Integer total, Integer rem);
	public Response issueBook(Integer id, String username, String on_counter);
	public Response returnBook(Integer id, String username, String on_counter);
	public Response toggleIssueReturn(Integer id, String username, String on_counter);
	public List<Book> getIssuedBooksByUsername(String username);
}