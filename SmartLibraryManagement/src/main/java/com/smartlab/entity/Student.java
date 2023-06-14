package com.smartlab.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;



    // One-to-Many relationship with Rental
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Rental> rentals;



	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Student(String studentName, String username, String password, List<Rental> rentals) {
		super();
		this.studentName = studentName;
		this.username = username;
		this.password = password;
		this.rentals = rentals;
	}



	public int getStudentId() {
		return studentId;
	}



//	public void setStudentId(int studentId) {
//		this.studentId = studentId;
//	}



	public String getStudentName() {
		return studentName;
	}



	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public List<Rental> getRentals() {
		return rentals;
	}



	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

   
}