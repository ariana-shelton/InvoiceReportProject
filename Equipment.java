package com.fmt;

import java.time.LocalDate;
import java.time.Period;

/**
 * 
 * This class models equipment
 *
 * 
 * 
 * @author shane
 * 
 */

public class Equipment extends Item {
	private String model;

	public Equipment(String code, String type, String name, String invoiceCode, String model) {
		super(code, type, name, invoiceCode);
		this.model = model;
	}

	public String getModel() {
		return this.model;
	}

	@Override
	public double getCost() {
		return 0;
	}

	@Override
	public double getTax() {
		return 0;
	}

	@Override
	public double getTotal() {
		return 0;
	}

	public String itemToString() {
		return this.getCode() + ",E," + this.getName() + "," + this.model;
	}

	@Override
	public double getQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUnit() {
		return null;
	}
	public String getToken() {
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
	public Double getUnitPrice() {
		// TODO Auto-generated method stub
		return null;
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

//	public abstract String getToken();

}