package com.fmt;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
/**
 * 
 * This class models a lease
 *
 * 
 * 
 * @author Ariana Shelton
 * 
 */

public class Leased extends Equipment {
	private String token;
	private Double fee;
	private LocalDate startDate;
	private LocalDate endDate;

	public Leased(Equipment e, String token, Double fee, String startDate, String endDate) {
		super(e.getCode(), e.getType(), e.getName(), e.getInvoiceCode(), e.getModel());
		this.token = token;
		this.fee = fee;
		this.startDate = LocalDate.parse(startDate);
		this.endDate = LocalDate.parse(endDate);
	}

	@Override
	public double getCost() {
		double days = getStartDate().until(getEndDate(), ChronoUnit.DAYS) + 1;
		double months = days / 30;
		double cost = this.fee * months;
		cost = cost * 100;
		Math.round(cost);
		cost = cost / 100;
		return cost;
	}

	@Override
	public double getTax() {
		if (getCost() < 10000) {
			return 0;
		} else if (getCost() >= 10000 && getCost() < 100000) {
			return 500;
		} else {
			return 1500;
		}
	}

	@Override
	public double getTotal() {
		return getCost() + getTax();
	}

	public String getToken() {
		return this.token;
	}

	public String toString() {
		return this.getInvoiceCode() + "," + this.getCode() + ",L," + this.getCost() + "," + this.startDate + ","
				+ this.endDate;
	}

	public Period getAge() {
		return Period.between(startDate, endDate);
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public double getFee() {
		return this.fee;
	}
}
