package com.fmt;

import java.util.List;

/**
 * 
 * This class models a store
 *
 * 
 * 
 * @author Ariana Shelton
 * 
 */

public class Store extends Address {

	private String storeCode;

	private String managerCode;

	private List<Invoice> invoices;

	public Store(String storeCode, String managerCode,

			String street, String city, String state, String zipCode, String country) {

		super(street, city, state, zipCode, country);

		this.storeCode = storeCode;

		this.managerCode = managerCode;

	}

	public String getStoreCode() {
		return this.storeCode;
	}

	public String getManagerCode() {
		return this.managerCode;
	}
	public void addInvoice(Invoice invoice) {
		invoices.add(invoice);
	}
	public String toString() {
		return this.storeCode + "," + this.managerCode +  "," + this.getStreet() +  "," + this.getCity() +  "," + this.getState() +  "," + this.getZipCode() + "," + this.getCountry();
	}

}
