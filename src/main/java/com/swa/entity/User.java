package com.swa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "User")
public class User {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",columnDefinition = "BIGINT(10) UNIQUE KEY auto_increment")
    private int id;
    
    @Id
    private String email;
    
    @Column(name="password",nullable = false)
    private String password;
    
    @Column(name="role",nullable = false)
    private String role;
    
    @Column(name="firstName",nullable = false)
    private String firstName;
    
    @Column(name="lastName")
    private String lastName;
    
    
    @Column(name="subject")
    private String subject;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
    
    
}
