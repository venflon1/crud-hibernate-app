package com.roberto.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This is a Java POJO - A normal class with a bit of java annotation
 * 
 * */
@Entity		// serve for tell hibernate that this java class must be mapped in a table 
@Table(name = "student")	// serve for tell hibernate that this java class named "HbStudent" must be mapped with db table named "student"
public class HbStudent implements Cloneable {
	
	@Id		// serve for tell hibernate that this field named "identifier" must be mapped with a column that is a primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// serve for tell hibernate that this field named "identifier" must be mapped AUTO_INCREMENT into db table
	@Column(name = "id")	 // serve for tell hibernate that this field named "identifier" must be mapped with a column named "id" into table "student"
	private long identifier;
	
	@Column(name = "first_name")
	private String name;
	
	@Column(name = "last_name")
	private String surname;
			
	@Column(name = "email")
	private String emailAddress;
	
	
	public HbStudent(String name, String surname, String emailAddress) {
		super();
		this.name = name;
		this.surname = surname;
		this.emailAddress = emailAddress;
	}
	
	public HbStudent() {
		this("default-name", "default-surname", "default@email.com");
	}

	public long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(long identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HbStudent [identifier=");
		builder.append(identifier);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", emailAddress=");
		builder.append(emailAddress);
		builder.append("]");
		return builder.toString();
	}

}
