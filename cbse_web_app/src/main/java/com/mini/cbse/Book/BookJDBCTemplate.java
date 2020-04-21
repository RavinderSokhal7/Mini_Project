package com.mini.cbse.Book;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mini.cbse.Response;

public class BookJDBCTemplate implements BookDAO{
	private JdbcTemplate jdbcTemplateObject;
	// QUERIES
	private String SELECT_ID = "SELECT * from books where id = ?";
	private String INSERT_BOOK = "INSERT into books (book_name, book_author, total_copies, rem_copies) values (?,?,?,?)";
	private String SELECT_ALL_BOOKS = "SELECT * from books";
	private String SEARCH_BOOKS = "SELECT * from books where UPPER(book_name) LIKE UPPER(?) or UPPER(book_author) LIKE UPPER(?)";
	private String DELETE_BOOK_ID = "DELETE from books where id = ?";
	private String UPDATE_BOOK = "UPDATE books set total_copies = ?, rem_copies = ? where id = ?";
	private String INSERT_ISSUE_LOGS = "INSERT into issue_logs (id,username,status,issued_by) values (?,?,'issued',?)";
	private String UPDATE_ISSUE_LOGS = "UPDATE issue_logs set status = 'returned', returned_by = ? where id = ? and username = ?";
	private String USER_ISSUE_LOGS = "SELECT * from  issue_logs,books where issue_logs.username = ? and issue_logs.status='issued' and issue_logs.id = books.id";
	private String USER_ISSUE_LOGS2 = "SELECT * from issue_logs,books where issue_logs.id = ? and issue_logs.username = ? and issue_logs.status='issued' and issue_logs.id = books.id";
	private String USER_ISSUE_LOGS3 = "SELECT * from books,issue_logs where issue_logs.username = ? and issue_logs.id = books.id;";
	
	public void setDataSource(DataSource dataSource) {
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public Response checkIfExists(Integer id) {
//		String SQL = "SELECT * from books where id = ?";
		List<Book> books = jdbcTemplateObject.query(SELECT_ID, new Object[]{id}, new BookMapper());
		if(books.size() == 1) {
			Response resp = new Response(true,"Book Exists");
			return resp;
		}
		else {
			Response resp = new Response(false,"Book Does Not Exists");
			return resp;
		}
}

	public Response create(String name, String author, Integer total, Integer rem) {
//		String SQL = "INSERT into books (book_name, book_author, total_copies, rem_copies) values (?,?,?,?)";
		jdbcTemplateObject.update( INSERT_BOOK, name, author, total, rem);
		System.out.println("Added a new Book: "+name+" - "+author+"\nCopies: "+total+", "+rem);
		Response resp = new Response(true,"Book Successfully Added");
		return resp;
	}

	public Book getBook(Integer bookid) {
//		String SQL = "SELECT * from books where id = ?";
		List<Book> books = jdbcTemplateObject.query(SELECT_ID, new Object[]{bookid}, new BookMapper());
		if(books.size() == 0) { return null; }
		return books.get(0);
	}

	public List<Book> listBooks() {
//		String SQL = "SELECT * from books";
		List <Book> books = jdbcTemplateObject.query(SELECT_ALL_BOOKS, new BookMapper());
		return books;
	}

	public List<Book> searchBook(String query) {
		query = '%'+query+'%';
//		String SQL = "SELECT * from books where UPPER(book_name) LIKE UPPER(?) or UPPER(book_author) LIKE UPPER(?)";
		List <Book> books = jdbcTemplateObject.query(SEARCH_BOOKS, new Object[] {query,query}, new BookMapper());
		return books;
	}

	public Response delete(Integer id) {
//		String SQL = "DELETE from books where id = ?";
		jdbcTemplateObject.update(DELETE_BOOK_ID, id);
		System.out.println("Deleted Book with ID = " + id);
		Response resp = new Response(true,"Book Successfully Deleted");
		return resp;
	}

	public Response update(Integer id, Integer total, Integer rem) {
		Response resp = checkIfExists(id);
		if(resp.status == true) {
//			String SQL = "UPDATE books set total_copies = ?, rem_copies = ? where id = ?";
			jdbcTemplateObject.update(UPDATE_BOOK, total,rem,id);
			System.out.println("Updated details of Book with ID = " + id);
			resp = new Response(true,"Book Successfully Updated");
			return resp;
		}
		else {
			resp = new Response(false,resp.message);
			return resp;
		}
	}

	public Response issueBook(Integer id,String username, String on_counter) {
//		String SQL = "INSERT into issue_logs (id,username,status,issued_by) values (?,?,'issued',?)";
		jdbcTemplateObject.update( INSERT_ISSUE_LOGS, id, username, on_counter);
		System.out.println("Book ID : "+id+"\nIssued By: "+username);
		Response resp = new Response(true,"Book Successfully Issued");
		return resp;
	}

	public Response returnBook(Integer id,String username, String on_counter) {
//		String SQL = "UPDATE issue_logs set status = 'returned', returned_by = ? where id = ? and username = ?";
		jdbcTemplateObject.update( UPDATE_ISSUE_LOGS, on_counter, id, username);
		System.out.println("Book ID: "+id+"Returned By: "+username);
		Response resp = new Response(true,"Book Successfully Returned");
		return resp;
	}
	
	public Response toggleIssueReturn(Integer id, String username, String on_counter) {
		//Checking if 3 books issued already or not!
//		String SQL = "SELECT * from  issue_logs,books where issue_logs.username = ? and issue_logs.status='issued' and issue_logs.id = books.id";
		List<Book> books = jdbcTemplateObject.query(USER_ISSUE_LOGS, new Object[] {username}, new BookMapper());
		if(books.size() <= 3) {
//			SQL = "SELECT * from issue_logs,books where issue_logs.id = ? and issue_logs.username = ? and issue_logs.status='issued' and issue_logs.id = books.id";
			books = jdbcTemplateObject.query(USER_ISSUE_LOGS2, new Object[] {id,username}, new BookMapper());
			Response resp;
			if(books.size() == 0) { resp = issueBook(id,username,on_counter); }
			else { resp = returnBook(id,username,on_counter); }
			return resp;
		}
		else {
			Response resp = new Response(false,"Already issued 3 books");
			return resp;
		}
	}
	
	public List<Book> getIssuedBooksByUsername(String username){
//		String SQL = "SELECT * from books,issue_logs where issue_logs.username = ? and issue_logs.id = books.id;";
		List <Book> books = jdbcTemplateObject.query(USER_ISSUE_LOGS3, new Object[] {username}, new BookMapper());
		return books;
	}
}