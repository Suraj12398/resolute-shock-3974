package com.smartlab.dao;

import java.util.List;
import java.util.Scanner;

import com.smartlab.entity.Book;
import com.smartlab.entity.Feedback;
import com.smartlab.entity.Rental;
import com.smartlab.entity.Student;
import com.smartlab.exception.NoRecordFoundException;
import com.smartlab.exception.SomethingWentWrongException;

public interface StudentDao {
	Student save(Student student);
//    Student findByUsername(String username);
    List<Book> findAvailableBooks() throws SomethingWentWrongException,NoRecordFoundException;
    List<Book> searchBooksByGenre(String genre) throws SomethingWentWrongException, NoRecordFoundException;
    void saveRental(Rental rental) throws SomethingWentWrongException, NoRecordFoundException;
    boolean updateRental(Rental rental) throws SomethingWentWrongException, NoRecordFoundException;
    void saveFeedback(Feedback feedback) throws SomethingWentWrongException, NoRecordFoundException;
	List<Book> searchBooksByTitle(String title) throws SomethingWentWrongException, NoRecordFoundException;
	void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException;
	Book findBookById(int id) throws SomethingWentWrongException, NoRecordFoundException;
	void changePassword(String stdPassword) throws SomethingWentWrongException, NoRecordFoundException;
	void updateBalance(long stdBalance) throws SomethingWentWrongException, NoRecordFoundException;
	void updateName(String stdName) throws SomethingWentWrongException, NoRecordFoundException;
}
