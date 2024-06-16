package com.fmt;








//YOU DONT NEED THIS CLASS








public class InvoiceItem {

	private String invoiceCode;
	private String itemCode;
	// for equipment:
	private String purOrLease;
	private double fee;
	private double price;
	private String startDate;
	private String endDate;
	// products or services:
	private double quanOrHours;

//	equipment lease:
	public InvoiceItem(String invoiceCode, String itemCode, String purOrLease, double fee, String startDate,
			String endDate) {
		this.invoiceCode = invoiceCode;
		this.itemCode = itemCode;
		this.purOrLease = purOrLease;
		this.fee = fee;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	// equipment purchase
	public InvoiceItem(String invoiceCode, String itemCode, String purOrLease, double price) {
		this.invoiceCode = invoiceCode;
		this.itemCode = itemCode;
		this.purOrLease = purOrLease;
		this.price = price;
	}

//product or services
	public InvoiceItem(String invoiceCode, String itemCode, double quanOrHours) {
		this.invoiceCode = invoiceCode;
		this.itemCode = itemCode;
		this.quanOrHours = quanOrHours;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public String getPurOrLease() {
		return purOrLease;
	}

	public double getFee() {
		return fee;
	}

	public double getPrice() {
		return price;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public double getQuanOrHours() {
		return quanOrHours;
	}

}
