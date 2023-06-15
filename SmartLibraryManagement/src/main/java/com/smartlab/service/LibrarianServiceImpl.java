package com.smartlab.service;

import java.util.List;

import com.smartlab.dao.LibrarianDao;
import com.smartlab.dao.LibrarianDaoImpl;
import com.smartlab.entity.Book;
import com.smartlab.entity.Feedback;
//import com.smartlab.entity.Librarian;
import com.smartlab.entity.Rental;

public class LibrarianServiceImpl implements LibrarianService {

	LibrarianDao ld= new LibrarianDaoImpl();
	
	
//	@Override
//	public Librarian registerLibrarian(String librarianName, String username, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public Librarian login(String username, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Book addBook(Book book) {
		// TODO Auto-generated method stub
		return ld.saveBook(book);
	}

	@Override
	public boolean updateBookInformation(Book book) {
		// TODO Auto-generated method stub
		return ld.updateBook(book);
	}

	@Override
	public boolean removeBook(int id) {
		// TODO Auto-generated method stub
		return ld.deleteBook(id);
	}

	@Override
	public List<Rental> viewStudentRentals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> viewBookFeedbacks() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
