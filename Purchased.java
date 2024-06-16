
package com.fmt;

/**
 * 
 * This class models a purchased item
 *
 * 
 * 
 * @author Ariana Shelton
 * 
 */
public class Purchased extends Equipment {

	private String token;

	private Double price;

	public Purchased(Equipment e, String token, Double price) {

		super(e.getCode(), e.getType(), e.getName(), e.getInvoiceCode(), e.getModel());

		this.token = token;

		this.price = price;

	}

	public double getCost() {

		return this.price;

	}

	@Override

	public double getTax() {

		return 0;

	}

	public String getToken() {
		return token;
	}

	@Override

	public double getTotal() {

		return getCost() + getTax();

	}

	public String toString() {
		return this.getInvoiceCode() + "," + this.getCode() + ",P," + this.getCost();
	}

}