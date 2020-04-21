package com.mini.cbse.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class BookMapper implements RowMapper<Book> {
   public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
      Book book = new Book();
      book.setID(rs.getInt("id"));
      book.setName(rs.getString("book_name"));
      book.setAuthor(rs.getString("book_author"));
      book.setTotal(rs.getInt("total_copies"));
      book.setRemaining(rs.getInt("rem_copies"));
      return book;
   }
}