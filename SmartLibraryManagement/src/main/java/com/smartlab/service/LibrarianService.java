package com.smartlab.service;

import java.util.List;

import com.smartlab.entity.Book;
import com.smartlab.entity.Feedback;
//import com.smartlab.entity.Librarian;
import com.smartlab.entity.Rental;
import com.smartlab.exception.NoRecordFoundException;
import com.smartlab.exception.SomethingWentWrongException;

public interface LibrarianService {
//		Librarian registerLibrarian(String librarianName, String username, String password);
//	    Librarian login(String username, String password);

//	    boolean updateBookInformation(Librarian librarian, Book book);
//	    boolean removeBook(Librarian librarian, Book book);
	    List<Rental> viewStudentRentals();
	    List<Feedback> viewBookFeedbacks() throws SomethingWentWrongException, NoRecordFoundException;
//	    void logout(Librarian librarian);
		Book addBook(Book book) throws SomethingWentWrongException, NoRecordFoundException;
		boolean removeBook(int id) throws SomethingWentWrongException ,NoRecordFoundException;
		boolean updateBookInformation(Book book) throws SomethingWentWrongException ,NoRecordFoundException;
}
