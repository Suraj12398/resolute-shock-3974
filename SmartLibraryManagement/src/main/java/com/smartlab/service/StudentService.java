package com.smartlab.service;

import java.util.List;

import com.smartlab.entity.Book;
import com.smartlab.entity.Feedback;
import com.smartlab.entity.Rental;
import com.smartlab.entity.Student;

public interface StudentService {
	 Student registerStudent(String studentName, String username, String password);
	    Student login(String username, String password);
	    List<Book> viewAvailableBooks();
	    List<Book> searchBooksByCriteria(String criteria);
	    Rental rentBook(Student student, Book book);
//	    boolean returnBook(Student student, Book book);
	    Feedback provideFeedback(Student student, Book book, String comment, int rating);
//	    void logout(Student student);
		boolean returnBook(Rental rental);
}
