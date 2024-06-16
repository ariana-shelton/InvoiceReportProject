package com.fmt;

import java.io.File;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * This class prints out summary by details
 *
 * 
 * 
 * @author Ariana Shelton
 * 
 */

public class InvoiceReport {
	static File invoiceItemsCSV = new File("data/InvoiceItems.csv");
	static File invoicesCSV = new File("data/Invoices.csv");
	static File storesCSV = new File("data/Stores.csv");
	static File personsCSV = new File("data/Persons.csv");
	static File itemsCSV = new File("data/Items.csv");
	static List<Invoice> invoices = DataConverter.loadInvoicesFile(invoicesCSV);
	static List<Store> stores = DataConverter.loadStoresFile(storesCSV);
	static List<Item> invoiceItems = DataConverter.loadInvoiceItemsFile(invoiceItemsCSV);
	static List<Person> people = DataConverter.loadPeopleFile(personsCSV);
	static List<Item> items = DataConverter.loadItemsFile(itemsCSV);

	public static void printByPerson() {
		System.out.println("Persons.csv:");
		System.out.println(people.size());
		for (int i = 0; i < people.size(); i++) {
			System.out.println(people.get(i).toString());
		}
	}

	public static void printByStore() {
		System.out.println("Stores.csv:");
		System.out.println(stores.size());
		for (int i = 0; i < stores.size(); i++) {
			System.out.println(stores.get(i).toString());
		}
	}

	public static void printByItem() {
		System.out.println("Items.csv:");
		System.out.println(items.size());
		for (int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i).itemToString());
		}
	}

	public static void printByInvoice() {
		System.out.println();
		System.out.println("Invoices.csv:");
		System.out.println(invoices.size());
		for (int i = 0; i < invoices.size(); i++) {
			System.out.println(invoices.get(i).toString());
		}
	}

	public static void printByInvoiceItem() {
		System.out.println("InvoiceItems.csv:");
		List<Item> invoiceItems = DataConverter.loadInvoiceItemsFile(invoiceItemsCSV);
		System.out.println(invoiceItems.size());
		for (int i = 0; i < invoiceItems.size(); i++) {
			System.out.println(invoiceItems.get(i).toString());
		}
	}

	public static void printSummaryReport() {
		System.out
				.println("+----------------------------------------------------------------------------------------+");
		System.out
				.println("| Summary Report - By Total                                                              |");
		System.out
				.println("+----------------------------------------------------------------------------------------+");
		System.out.println("Invoice #  Store      Customer                  Num Items       Tax       Total");
		double total2 = 0;
		int itemCount2 = 0;
		double tax2 = 0;
		for (int i = 0; i < invoices.size(); i++) {
			int itemCount = 0;
			double tax = 0;
			double total = 0;
			for (int j = 0; j < invoiceItems.size(); j++) {
				if (invoiceItems.get(j).getInvoiceCode().equals(invoices.get(i).getInvoiceCode())) {
					itemCount++;
					tax += invoiceItems.get(j).getTax();
					total += invoiceItems.get(j).getTotal();
					itemCount2++;
					tax2 += invoiceItems.get(j).getTax();
					total2 += invoiceItems.get(j).getTotal();
				}
			}
			System.out.printf("%6s     %6s     %-20s	%d   	  $%10.2f  $%10.2f \n",
					invoices.get(i).getInvoiceCode(), invoices.get(i).getStore().getStoreCode(),
					invoices.get(i).getCustomer().toStringName(), itemCount, tax, total);
		}
		System.out
				.println("+----------------------------------------------------------------------------------------+");
		System.out.printf(" 			    		        %d   	  $%10.2f  $%10.2f \n", itemCount2, tax2, total2);
	}

	public static void printStoreSummary() {
		System.out.println("+----------------------------------------------------------------+");
		System.out.println("Store Sales Summary Report ");
		System.out.println("+----------------------------------------------------------------+");
		System.out.println("Store     Manager               # Sales    Grand Total   ");
		int numSales2 = 0;
		double total2 = 0;
		for (int i = 0; i < stores.size(); i++) {
			int numSales = 0;
			double total = 0;
			String managerName = "";
			for (int j = 0; j < invoices.size(); j++) {
				if (invoices.get(j).getStore().getStoreCode().equals(stores.get(i).getStoreCode())) {
					numSales++;
					numSales2++;
					for (int k = 0; k < invoiceItems.size(); k++) {
						if (invoiceItems.get(k).getInvoiceCode().equals(invoices.get(j).getInvoiceCode())) {
							total += invoiceItems.get(k).getTotal();
							total2 += invoiceItems.get(k).getTotal();
						}
					}
				}
				for (int k = 0; k < people.size(); k++) {
					if (stores.get(i).getManagerCode().equals(people.get(k).getPersonCode())) {
						managerName = people.get(k).toStringName();
					}
				}
			}
			System.out.printf("%6s	  %-20s	%d   	   $%10.2f \n", stores.get(i).getStoreCode(), managerName, numSales,
					total);
		}
		System.out
				.println("+----------------------------------------------------------------------------------------+");
		System.out.printf(" 				%d	   $%10.2f \n", numSales2, total2);
	}

	public static void printInvoices() {
		for (int i = 0; i < invoices.size(); i++) {
			double subtotal = 0.0;
			double tax = 0.0;
			double grandTotal = 0.0;
			System.out.println("Invoice: #" + invoices.get(i).getInvoiceCode());
			System.out.println("Store:   #" + invoices.get(i).getStore().getStoreCode());
			System.out.println("Date:    " + invoices.get(i).getDate());
			for (int j = 0; j < people.size(); j++) {
				if (people.get(j).getPersonCode().equals(invoices.get(i).getCustomer().getPersonCode())) {
					System.out.println("Customer:");
					System.out.println(people.get(j).toStringName() + " (" + people.get(j).getPersonCode() + " : "
							+ people.get(j).getEmails() + ")");
					System.out.println("    " + people.get(j).getStreet());
					System.out.println("    " + people.get(j).getCity() + " " + people.get(j).getCountry() + " "
							+ people.get(j).getZipCode() + " " + people.get(j).getCountry());
					System.out.println();
				}
				if (people.get(j).getPersonCode().equals(invoices.get(i).getSalesPerson().getPersonCode())) {
					System.out.println("Sales Person:");
					System.out.println(people.get(j).toStringName() + " (" + people.get(j).getPersonCode() + " : "
							+ people.get(j).getEmails() + ")");
					System.out.println("    " + people.get(j).getStreet());
					System.out.println("    " + people.get(j).getCity() + " " + people.get(j).getCountry() + " "
							+ people.get(j).getZipCode() + " " + people.get(j).getCountry());
					System.out.println();
				}
			}
			System.out.println("Item                                              Total");
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-         -=-=-=-=-=-");
			for (int j = 0; j < invoiceItems.size(); j++) {
				if (invoiceItems.get(j).getInvoiceCode().equals(invoices.get(i).getInvoiceCode())) {
					if (invoiceItems.get(j).getType().equals("P")) {
						System.out.println(
								invoiceItems.get(j).getCode() + "		(Product)	" + invoiceItems.get(j).getName());
						System.out.printf("      %.0f @ $%.2f/%s\n", invoiceItems.get(j).getQuantity(),
								invoiceItems.get(j).getUnitPrice(), invoiceItems.get(j).getUnit());
						System.out.printf("                                 		 $%10.2f\n",
								invoiceItems.get(j).getCost());
						subtotal += invoiceItems.get(j).getCost();
						tax += invoiceItems.get(j).getTax();
						grandTotal += invoiceItems.get(j).getTotal();
					}
					if (invoiceItems.get(j).getType().equals("E")) {
						if (invoiceItems.get(j).getToken().equals("P")) {
							System.out.println(invoiceItems.get(j).getCode() + "		(Purchased)	"
									+ invoiceItems.get(j).getName() + "-" + invoiceItems.get(j).getModel());
							System.out.printf("                                 		 $%10.2f\n",
									invoiceItems.get(j).getCost());
							subtotal += invoiceItems.get(j).getCost();
							tax += invoiceItems.get(j).getTax();
							grandTotal += invoiceItems.get(j).getTotal();
						}
						if (invoiceItems.get(j).getToken().equals("L")) {
							System.out.println(invoiceItems.get(j).getCode() + "		(Leased)	"
									+ invoiceItems.get(j).getName() + "-" + invoiceItems.get(j).getModel());
							long days = ChronoUnit.DAYS.between(invoiceItems.get(j).getStartDate(),
									invoiceItems.get(j).getEndDate()) + 1;
							System.out.printf("	%d days (%s -> %s) @ $%.2f/30 days\n", days,
									invoiceItems.get(j).getStartDate().toString(),
									invoiceItems.get(j).getEndDate().toString(), invoiceItems.get(j).getFee());
							System.out.printf("                                 		 $%10.2f\n",
									invoiceItems.get(j).getCost());
							subtotal += invoiceItems.get(j).getCost();
							tax += invoiceItems.get(j).getTax();
							grandTotal += invoiceItems.get(j).getTotal();
						}

					}
					if (invoiceItems.get(j).getType().equals("S")) {
						System.out.println(invoiceItems.get(j).getCode() + "		(Service)		"
								+ invoiceItems.get(j).getName());
						System.out.printf("		%.1f hours @ $%.2f/hr\n", invoiceItems.get(j).getHours(),
								invoiceItems.get(j).getHourlyRate());
						System.out.printf("                                 		 $%10.2f\n",
								invoiceItems.get(j).getCost());
						subtotal += invoiceItems.get(j).getCost();
						tax += invoiceItems.get(j).getTax();
						grandTotal += invoiceItems.get(j).getTotal();

					}

					System.out.println();
				}
			}
			System.out.println("                                 		 -=-=-=-=-=-");
			System.out.printf("					Subtotal $%10.2f\n", subtotal);
			System.out.printf("        			             Tax $%10.2f\n", tax);
			System.out.printf("				     Grand Total $%10.2f\n", grandTotal);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		printByPerson();
		printByStore();
		printByItem();
		printByInvoice();
		printByInvoiceItem();
		printSummaryReport();
		printStoreSummary();
		printInvoices();
	}
}
