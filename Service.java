package com.fmt;

import java.time.LocalDate;
import java.time.Period;

/**
 * 
 * This class models a service
 *
 * 
 * 
 * @author shane
 * 
 */

public class Service extends Item {

	private Double hourlyRate;

	private Double hours;

	public Service(String code, String type, String name, String invoiceCode, Double hourlyRate) {

		super(code, type, name, invoiceCode);

		this.hourlyRate = hourlyRate;

		this.hours = null;

	}

//Service invoice

	public Service(Service s, Double hours) {

		super(s.getCode(), s.getType(), s.getName(), s.getInvoiceCode());

		this.hourlyRate = s.getHourlyRate();

		this.hours = hours;

	}

	public Double getHourlyRate() {

		return hourlyRate;

	}

	@Override

	public double getCost() {

		return getHourlyRate() * hours;

	}

	@Override

	public double getTax() {

		return getCost() * 0.0345;

	}

	@Override

	public double getTotal() {

		return getCost() + getTax();

	}

	public double getHours() {
		return this.hours;
	}

	public String toString() {
		return this.getInvoiceCode() + "," + this.getCode() + "," + hours;
	}

	public String itemToString() {
		return this.getCode() + ",S," + this.getName() + "," + this.getHourlyRate();
	}

	public String getToken() {
		return null;
	}

	@Override
	public double getQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUnit() {
		// TODO Auto-generated method stub
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
	public Double getUnitPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getFee() {
		// TODO Auto-generated method stub
		return 0;
	}
}