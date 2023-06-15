package com.smartlab.ui;

import java.util.List;

import com.smartlab.dao.LibrarianDao;
import com.smartlab.dao.LibrarianDaoImpl;
import com.smartlab.entity.Book;

public class LibrarianUi {

	private static void addBook() {
		// TODO Auto-generated method stub
		System.out.println("PLease Add Book");
		Book book=new Book("ikigai","Hector Fracia","tan",false, null);
		
		LibrarianDao ld=new LibrarianDaoImpl();
		Book t=ld.saveBook(book);
		if(t==null) {
			System.out.println("no book added");
		}else {
			System.out.println("book added successfull");
		}
	}
	private static void updateBook() {
		// TODO Auto-generated method stub
		System.out.println("PLease Add Book");
		Book book=new Book("Impossible Dreams","Rohan Dutta","Self help",true, null);
		book.setBookId(1);
		LibrarianDao ld=new LibrarianDaoImpl();
		boolean t=ld.updateBook(book);
		if(t==false) {
			System.out.println("no book updated");
		}else {
			System.out.println("book updated successfull");
		}
	}
	private static void deleteBook() {
		// TODO Auto-generated method stub
		int id=2;
		LibrarianDao ld=new LibrarianDaoImpl();
		ld.deleteBook(id);
		
	}
	private static void viewList() {
		// TODO Auto-generated method stub
		LibrarianDao ld=new LibrarianDaoImpl();
		List<Book> bookList=ld.viewBookAvailable();
		
		for(Book b:bookList) {
			System.out.println(b.toString());
		}
	}
public static void main(String[] args) {
	System.out.println("You are In Library Ui");
//	addBook();
//	updateBook();
//	deleteBook();
//	viewList();
}
}
