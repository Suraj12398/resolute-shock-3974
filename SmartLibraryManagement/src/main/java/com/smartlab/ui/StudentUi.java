package com.smartlab.ui;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import com.smartlab.dao.LibrarianDao;
import com.smartlab.dao.LibrarianDaoImpl;
import com.smartlab.dao.StudentDao;
import com.smartlab.dao.StudentDaoImpl;
import com.smartlab.entity.Book;
import com.smartlab.entity.Feedback;
import com.smartlab.entity.Rental;
import com.smartlab.entity.SessionStd;
import com.smartlab.entity.Student;
import com.smartlab.exception.NoRecordFoundException;
import com.smartlab.exception.SomethingWentWrongException;

public class StudentUi {
	
	
	private static void availableBook() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		StudentDao sd=new StudentDaoImpl();
		
		List<Book> bookList=sd.findAvailableBooks();;
		CommandLineTable st = new CommandLineTable();
		st.setShowVerticalLines(true);
		System.out.println("\n");
		st.setHeaders("Id", "Title", "Author","Genre","Feedbacks");

//	
		for(Book b:bookList) {
			String str="";
			StringJoiner joiner = new StringJoiner(", "); 
			b.getFeedbacks().forEach(a -> joiner.add(a.getComment()));

			String feedbacksString = joiner.toString();
			str += feedbacksString + " ";
			st.addRow(""+b.getBookId(), b.getTitle() , b.getAuthor() , b.getGenre(),str);
			
			}
		st.print();
		System.out.println(); 
		
	}
	
	private static void searchByGenre(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		StudentDao sd=new StudentDaoImpl();
		System.out.println("Enter Genre you waant to search");
		String genre=sc.next();
		List<Book> bookList=sd.searchBooksByGenre(genre);
		CommandLineTable st = new CommandLineTable();
		st.setShowVerticalLines(true);
		System.out.println("\n");
		st.setHeaders("Id", "Title", "Author","Genre","Feedbacks");

		st.print();
		System.out.println(); 
		if(bookList.size()==0) {
			System.out.println("No Book Available");
		}else {
			for(Book b:bookList) {
				String str="";
				StringJoiner joiner = new StringJoiner(", "); 
				b.getFeedbacks().forEach(a -> joiner.add(a.getComment()));

				String feedbacksString = joiner.toString();
				str += feedbacksString + " ";
				st.addRow(""+b.getBookId(), b.getTitle() , b.getAuthor() , b.getGenre(),str);
				
				}	
		}
		
		
	}
	private static void searchByTitle(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		StudentDao sd=new StudentDaoImpl();
		System.out.println("Enter Title of book You want to Search");
		String title=sc.next();
		List<Book> bookList=sd.searchBooksByTitle(title);
		
		CommandLineTable st = new CommandLineTable();
		st.setShowVerticalLines(true);
		System.out.println("\n");
		st.setHeaders("Id", "Title", "Author","Genre","Feedbacks");

		st.print();
		System.out.println(); 
		if(bookList.size()==0) {
			System.out.println("No Book Available");
		}else {
			for(Book b:bookList) {
				String str="";
				StringJoiner joiner = new StringJoiner(", "); 
				b.getFeedbacks().forEach(a -> joiner.add(a.getComment()));

				String feedbacksString = joiner.toString();
				str += feedbacksString + " ";
				st.addRow(""+b.getBookId(), b.getTitle() , b.getAuthor() , b.getGenre(),str);
				
				}	
			}
		
	}
	private static void registerStudent(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Please Add Student");
//		Student std1=new Student("Suraj Deosarkar","sd","sd",new ArrayList<Rental>());
//		Student std2=new Student("sumit kumar","sk","sk",new ArrayList<Rental>());
//		Student std3=new Student("Pavan Deshmukh","pd","pd",new ArrayList<Rental>());
//		Student std4=new Student("Pankaj Shinde","ps","ps",new ArrayList<Rental>());
//		Student std5=new Student("Pankaj Anand","pa","pa",new ArrayList<Rental>());
		StudentDao ls=new StudentDaoImpl();
		System.out.println("Enter Student Name");
		String name=sc.next();
		System.out.println("Enter Username");
		String username=sc.next();
		System.out.println("Enter Password");
		String password=sc.next();
		Student std=new Student(name,username,password,new ArrayList<Rental>());
		Student t=ls.save(std);
//		ls.save(std2);
//		ls.save(std3);
//		ls.save(std4);
//		ls.save(std5);
		if(t==null) {
			System.out.println("no Student added");
		}else {
			System.out.println("Student added successfull");
		}
		
	}
	private static void loginStudent(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
//		Scanner sc= new Scanner(System.in);
//		
		System.out.println("Enter Your Username");
		String username=sc.next();
		System.out.println("Enter Your Password");
		String password=sc.next();
		
		
//		String username="sk";
//		String password="sk";
		
	StudentDao ls=new StudentDaoImpl();
	ls.login(username,password);
		
		
		System.out.println("Welcome "+ SessionStd.getCurrentStd().getStudentName());
		String choice = "0";
		do {
			System.out.println("1. List of Book Available");
			System.out.println("2. Search Book by Genre");
			System.out.println("3. Search Book by Title");
			System.out.println("4. Rent a Book");
			System.out.println("5. Return Book");
			System.out.println("6. Give Feedback for Book");
			System.out.println("7. Delete Account");
//			System.out.println("8. View List of Book");
			System.out.println("0. Exit");
			System.out.print("Enter Selection ");
			choice = sc.next();
			switch(choice) {
				case "1":
					availableBook();
					break;
				case "2":
					searchByGenre(sc);
					break;
				case "3":
					searchByTitle(sc);
					break;
				case "4":
					bookRent(sc);
					break;
				case "5":
					returnBook(sc);
					break;
				case "6":
					giveFeedback(sc);
					break;
				case "7":
					deleteStudent(sc);
					break;
//				case 8:
//					viewList();
//					break;
				case "0":
					System.out.println("Thanks for using the services");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(choice != "0");
		sc.close();
		
	}
	private static void deleteStudent(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		System.out.println("Are You Sure You want to Delete Your Account");
		System.out.println("Enter y for confirmation enter n if not want to delete");
		String cnf=sc.next();
		if(cnf.equals("y")) {
			int id=SessionStd.getCurrentStd().getStudentId();
			LibrarianDao ld=new LibrarianDaoImpl();
			if(ld.deleteStudent(id)) {
				System.out.println("Deleted Successfully");
				StudentUi.main(null);
			}else{
				System.out.println("Student Not Found");
			}
		}else if (cnf.equals("n")) {
			System.out.println("Thank you so much");
		}else {
			System.out.println("Invalid Selection");
			deleteStudent(sc);
		}
		
		
	}

	private static void bookRent(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		StudentDao ls=new StudentDaoImpl();
		ls.findAvailableBooks().forEach(a->System.out.println(a));
		System.out.println("Enter Book Id You want to Rent");
		int id=sc.nextInt();
		StudentDao st=new StudentDaoImpl();
		Book book=st.findBookById(id);

		Rental rental=new Rental(SessionStd.getCurrentStd(),book,Date.valueOf(LocalDate.now()),null);
		st.saveRental(rental);
	}
	private static void returnBook(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		StudentDao ls=new StudentDaoImpl();
//		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Rental Id You want to Return");
		int id=sc.nextInt();
		System.out.println("Enter Book Id You want to Return");
		int idb=sc.nextInt();
//		int id=1;
//		int idb=1;
		StudentDao st=new StudentDaoImpl();
		Book book=st.findBookById(idb);

		Rental rental=new Rental(SessionStd.getCurrentStd(),book,Date.valueOf(LocalDate.now()),Date.valueOf("2023-07-23"));
		
//		Rental rental=new Rental(null,book,Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now()));
		rental.setRentalId(id);
		st.updateRental(rental);
		
		
	}
	private static void giveFeedback(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		StudentDao ls=new StudentDaoImpl();

		int id=sc.nextInt();
		StudentDao st=new StudentDaoImpl();
		Book book=st.findBookById(id);
		String msg="well done";
		Feedback feedback=new Feedback(SessionStd.getCurrentStd(),book,msg,10);
		st.saveFeedback(feedback);
	}
	
	
public static void main(String[] args) throws SomethingWentWrongException,NoRecordFoundException {
	Scanner sc= new Scanner(System.in);
	System.out.println("Welcome");
	int choice = 0;
	do {
		System.out.println("1. Sign In");
		System.out.println("2. Register new Student");
		System.out.println("3. Back to Main Menu");
		System.out.println("0. Exit");
		System.out.print("Enter Selection ");
		choice = sc.nextInt();
		switch(choice) {
			case 1:
				loginStudent(sc);
				break;
			case 2:
				registerStudent(sc);
				break;
			case 3:
				MainLab.main(null);
				break;
			
			case 0:
				System.out.println("Thanks for using the services");
				break;
			default:
				System.out.println("Invalid Selection, try again");
		}
	}while(choice != 0);
	sc.close();
//			loginStudent(sc);
			
			
			
		
	
	
	
//	availableBook();
//	registerStudent();
//	searchByGenre();
//	searchByTitle();
//	loginStudent();
//	bookRent();
//	returnBook();
//	giveFeedback();
}














}
