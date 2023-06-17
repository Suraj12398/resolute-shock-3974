package com.smartlab.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.smartlab.entity.Book;
import com.smartlab.entity.Feedback;
import com.smartlab.entity.Rental;
import com.smartlab.entity.SessionStd;
import com.smartlab.entity.Student;
import com.smartlab.exception.NoRecordFoundException;
import com.smartlab.exception.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class StudentDaoImpl implements StudentDao {

	@Override
	public Student save(Student student) {
		// TODO Auto-generated method stub
		EntityManager em = null;
		EntityTransaction et=null;
		try {
			em = EMUtils.getEntityManager();
			et = em.getTransaction();
			et.begin();
			em.persist(student);
			et.commit();
		}catch(PersistenceException ex) {
			System.out.println("Duplicate entry");
			ex.getMessage();
		}finally{
			em.close();
		}
		return student;
	}

//	@Override
//	public Student findByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Book> findAvailableBooks() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		LibrarianDao ld= new LibrarianDaoImpl();
		return ld.viewBookAvailable();
	}
	
	
	@Override
public Book findBookById(int id) {
		EntityManager em = null;
//		EntityTransaction et=null;
		try {
			em = EMUtils.getEntityManager();
			Book bookdb=em.find(Book.class, id);
			if(bookdb==null) {
				System.out.println("Please enter Valid Book details");
			}
			else {
				return bookdb;
			}
			
		}catch(PersistenceException ex) {
			ex.getMessage();
		}finally{
			em.close();
		}
	return null;
	
}
	
	
	@Override
	public List<Book> searchBooksByGenre(String genre)throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub

		EntityManager em = null;
		List<Book> bookList=new ArrayList<Book>();
		try {
			
			em = EMUtils.getEntityManager();
			Query query=em.createQuery("SELECT b From Book b where b.genre LIKE :genre");
			query.setParameter("genre", "%"+genre +"%");
			bookList= query.getResultList();
			
			if(bookList.isEmpty()) {
				throw new NoRecordFoundException("No Book Found");
			}
			EntityTransaction et = em.getTransaction();
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}finally{
			em.close();
		}

return bookList;
	}
	@Override
	public List<Book> searchBooksByTitle(String title) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub

		EntityManager em = null;
		List<Book> bookList=new ArrayList<Book>();
		try {
			
			em = EMUtils.getEntityManager();
			Query query=em.createQuery("SELECT b From Book b where b.title LIKE :title");
			query.setParameter("title", "%"+title+"%");
			bookList= query.getResultList();
			EntityTransaction et = em.getTransaction();
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}finally{
			em.close();
		}

return bookList;
	}
	@Override
	public void saveRental(Rental rental) throws SomethingWentWrongException, NoRecordFoundException{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				EntityManager em = null;
				EntityTransaction et=null;
				try {
					rental.getBook().setAvailability(true);
					em = EMUtils.getEntityManager();
					et = em.getTransaction();
					et.begin();
					em.persist(rental);
					et.commit();
				}catch(PersistenceException ex) {
					System.out.println("Duplicate entry");
					ex.getMessage();
				}finally{
					em.close();
				}

	}

	@Override
	public void updateRental(Rental rental)throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		EntityManager em = null;
		EntityTransaction et=null;
		try {
			em = EMUtils.getEntityManager();
			Rental rent=em.find(Rental.class, rental.getRentalId());
			if(rent==null) {
				System.out.println("Please enter correct rental id");
			}
			else {
				et = em.getTransaction();
				et.begin();

				rent.setReturnDate(rental.getReturnDate());
				long differenceInReturn=rent.getReturnDate().getTime() - rent.getRentalDate().getTime();
				
				System.out.println(TimeUnit.MILLISECONDS.toDays(differenceInReturn));
				if(TimeUnit.MILLISECONDS.toDays(differenceInReturn)>7) {
					rent.setFine((TimeUnit.MILLISECONDS.toDays(differenceInReturn)/7)*5);
				}else {
					rent.setFine(0);
				}
				et.commit();
			}
			
		}catch(PersistenceException ex) {
			ex.getMessage();
		}finally{
			em.close();
		}
	}

	

	@Override
	public void saveFeedback(Feedback feedback) throws SomethingWentWrongException, NoRecordFoundException{
		// TODO Auto-generated method stub
		EntityManager em = null;
		EntityTransaction et=null;
		try {
			em = EMUtils.getEntityManager();
			et = em.getTransaction();
			feedback.getBook().getFeedbacks().add(feedback);
			feedback.getStudent().getFeedbacks().add(feedback);
			et.begin();
			em.persist(feedback);
			et.commit();
		}catch(PersistenceException ex) {
			System.out.println("Duplicate entry");
			ex.getMessage();
		}finally{
			em.close();
		}
	}

	@Override
	public void login(String username,String password) throws SomethingWentWrongException, NoRecordFoundException{
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();
			
			Query query = em.createQuery("SELECT c FROM Student c WHERE username = :username AND password = :password AND isDeleted = 0");
			query.setParameter("username", username);
			query.setParameter("password", password);
			
			List<Student> listStd = (List<Student>)query.getResultList();
//			System.out.println(listStd);
			if(listStd == null) {
				
				System.out.println("The username or password is Incorrect");
				
			}

			for(Student std: listStd) {
				SessionStd.setCurrentStd(std);
				System.out.println("Log in Successfull");
			}
			
		}catch(PersistenceException ex) {
			ex.getMessage();

		}finally{
			em.close();
		}
		
	}

}
