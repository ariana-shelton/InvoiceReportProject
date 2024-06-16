package com.fmt;

import java.time.LocalDate;
import java.time.Period;

/**
 * 
 * This class models a item
 *
 * 
 * 
 * @author shane
 * 
 */

public abstract class Item {

	private String code;

	private String type;

	private String name;
	private String invoiceCode;

	public Item(String code, String type, String name, String invoiceCode) {

//		super();

		this.code = code;

		this.type = type;

		this.name = name;
		this.invoiceCode = invoiceCode;

	}

//	public Item(Item i, String invoiceCode) {
//		super();
//		this.invoiceCode = invoiceCode;
//	}

	public String getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public abstract double getCost();

	public abstract double getTax();

	public abstract double getTotal();

	public abstract double getQuantity();
	public abstract double getHours();
	public abstract double getFee();
	public abstract Double getHourlyRate();
	public abstract Double getUnitPrice();

	public abstract String getUnit();

	public abstract String getToken();

	public abstract String getModel();
	public abstract Period getAge();
	public abstract LocalDate getStartDate();
	public abstract LocalDate getEndDate();

	public String itemToString() {
		return "";
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceC) {
		invoiceCode = invoiceC;
	}

//	public void setInvoiceCode(String invoiceCode) {
//		this.invoiceCode = invoiceCode;
//	}
}
