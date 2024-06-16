package com.fmt;

import java.time.LocalDate;
import java.time.Period;

/**
 * 
 * This class models a product
 *
 * 
 * 
 * @author shane
 * 
 */

public class Product extends Item {

	private String unit;

	private Double unitPrice;

	private Double quantity;


	public Product(String code, String type, String name, String invoiceCode, String unit, Double unitPrice) {

		super(code, type, name, invoiceCode);

		this.unit = unit;

		this.unitPrice = unitPrice;

		this.quantity = null;

	}

	public Product(Product p, Double quantity) {

		super(p.getCode(), p.getType(), p.getName(), p.getInvoiceCode());

		this.unit = p.getUnit();

		this.unitPrice = p.getUnitPrice();

		this.quantity = quantity;

	}

	public String getUnit() {

		return this.unit;

	}

	public Double getUnitPrice() {

		return this.unitPrice;

	}

	@Override

	public double getCost() {

		return getUnitPrice() * quantity;

	}

	@Override

	public double getTax() {

		return getCost() * 0.0715;

	}

	@Override

	public double getTotal() {

		return getCost() + getTax();

	}

	

	public String toString() {
		return this.getInvoiceCode() + "," + this.getCode() + "," + getTotal();
	}

	public String itemToString() {
		return this.getCode() + ",P," + this.getName() + "," + this.getUnit() + "," + this.getUnitPrice();
	}

	public double getQuantity() {
		return this.quantity;
	}
	public String getToken() {
		return null;
	}

	@Override
	public String getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Period getAge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate getStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate getEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getHours() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double getHourlyRate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getFee() {
		// TODO Auto-generated method stub
		return 0;
	}
}
