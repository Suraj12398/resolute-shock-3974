package com.smartlab.dao;

import java.util.ArrayList;
import java.util.List;

//import com.masai.dao.EMUtils;
//import com.masai.exception.SomeThingWentWrongException;
import com.smartlab.entity.Book;
import com.smartlab.entity.Feedback;
//import com.smartlab.entity.Librarian;
import com.smartlab.entity.Rental;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;


public class LibrarianDaoImpl implements LibrarianDao {

//	@Override
//	public Librarian save(Librarian librarian) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Librarian findByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Book saveBook(Book book) {
		
		EntityManager em = null;
		EntityTransaction et=null;
		try {
			em = EMUtils.getEntityManager();
			et = em.getTransaction();
			et.begin();
			em.persist(book);
			et.commit();
		}catch(PersistenceException ex) {
			System.out.println("Duplicate entry");
			ex.getMessage();
		}finally{
			em.close();
		}
		return book;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateBook(Book book) {
		EntityManager em = null;
		EntityTransaction et=null;
		try {
			em = EMUtils.getEntityManager();
			Book bookdb=em.find(Book.class, book.getBookId());
			
			
			if(bookdb==null) {
				System.out.println("Please enter Valid Book details");
			}
			else {
				et = em.getTransaction();
				et.begin();
				bookdb.setAuthor(book.getAuthor());
				bookdb.setGenre(book.getGenre());
				bookdb.setTitle(book.getTitle());
//				bookdb.setLibrarian(book.getLibrarian());
				
				et.commit();
			}
			
		}catch(PersistenceException ex) {
			ex.getMessage();
		}finally{
			em.close();
		}
		
		// TODO Auto-generated method stub
		
		return true;
	}

	@Override
	public boolean deleteBook(int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();
			Book bookD = em.find(Book.class,id );
			if(bookD==null) {
				System.out.println("Please enter Valid Book Id");
				flag=false;
			}else {
			EntityTransaction et = em.getTransaction();
			et.begin();
			bookD.setAvailability(false);
			et.commit();
			flag=true;
			}
		}catch(PersistenceException ex) {
				ex.getMessage();
		}finally{
			em.close();
		}
		return flag;
	}

	@Override
	public List<Rental> findStudentRentals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> findBookFeedbacks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> viewBookAvailable() {
		
				EntityManager em = null;
				List<Book> bookList=new ArrayList<Book>();
				try {
					
					em = EMUtils.getEntityManager();
					Query query=em.createQuery("SELECT b From Book b where b.availability=true");
					bookList= query.getResultList();
					EntityTransaction et = em.getTransaction();
					
				}catch(PersistenceException ex) {
					ex.printStackTrace();
				}finally{
					em.close();
				}
		
		return bookList;
	}

	

}
