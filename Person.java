package com.fmt;

/**
 * This class models a person and their info
 * 
 * @author shane
 */

import java.util.ArrayList;
import java.util.List;

public class Person extends Address{
	private String personCode;
	private String firstName;
	private String lastName;
	private List<String> emails;
	

	
	public Person(String personCode,String lastName, String firstName, 
			String street, String city, String state, String zipCode, String country, List<String> emails) {
		super(street, city, state, zipCode, country);
		this.personCode = personCode;
		this.lastName = lastName;
		this.firstName = firstName;
		this.emails = new ArrayList<>();
	}

	public String getPersonCode() {
		return this.personCode;
	}

	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void addEmail(String email) {
		this.emails.add(email);
	}

	public List<String> getEmails() {
		return emails;
	}
	public String printEmails() {
		List<String> emails = getEmails();
		String string = null;
		for (String email : emails) {
			if (string == null) {
				string = "," + email;
			}
			else {
				string = string + "," + email;
			}				
		}
		if (string == null) {
			return "";
		}
		else {
		return string;
		}
	}
	public String toStringName() {
		return this.lastName + ", " + this.firstName;
	}
	public String toString() {
		return this.personCode + "," + this.lastName + "," + this.firstName + "," + this.getStreet() + "," + this.getCity() + "," + this.getState() + "," + this.getZipCode() + "," + this.getCountry() +  this.printEmails();
	}


	
}
