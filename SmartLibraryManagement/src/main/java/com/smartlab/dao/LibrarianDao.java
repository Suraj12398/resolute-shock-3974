package com.smartlab.dao;

import java.util.List;
import java.util.Set;

import com.smartlab.entity.Book;
import com.smartlab.entity.Feedback;
//import com.smartlab.entity.Librarian;
import com.smartlab.entity.Rental;

public interface LibrarianDao {
//		Librarian save(Librarian librarian);
//	    Librarian findByUsername(String username);
	    Book saveBook(Book book);
	    boolean updateBook(Book book);
	    List<Book> viewBookAvailable();
	    List<Rental> findStudentRentals();
	    List<Feedback> findBookFeedbacks();
		boolean deleteBook(int id);
}
