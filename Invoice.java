package com.fmt;

import java.time.LocalDate;


public class Invoice {

	private String invoiceCode;

	private Store store;

	private Person customer;

	private Person salesPerson;

	private LocalDate date;

	public Invoice(String invoiceCode, Store store, Person customer, Person salesPerson, LocalDate date) {

		this.invoiceCode = invoiceCode;

		this.store = store;

		this.customer = customer;

		this.salesPerson = salesPerson;

		this.date = date;

	}

	public String getInvoiceCode() {

		return this.invoiceCode;

	}

	public Person getCustomer() {

		return this.customer;

	}

	public Person getSalesPerson() {

		return this.salesPerson;

	}

	public LocalDate getDate() {

		return this.date;

	}
	public Store getStore() {
		return this.store;
	}

	public String toString() {
		return this.invoiceCode + "," + this.store.getStoreCode() + "," + this.customer.getPersonCode() + "," + this.salesPerson.getPersonCode()
				+ "," + this.date;
	}

}