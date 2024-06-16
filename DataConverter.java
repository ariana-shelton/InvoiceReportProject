package com.fmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This class loads in data from an items file, persons file, and stores file
 * 
 * @author Ariana Shelton
 */
public class DataConverter {
	static File storesCSV = new File("data/Stores.csv");
	static File personsCSV = new File("data/Persons.csv");
	static File itemsCSV = new File("data/Items.csv");
	static File invoicesCSV = new File("data/Invoices.csv");
	static File invoiceItemsCSV = new File("data/InvoiceItems.csv");

	// Loads store data
	public static List<Store> loadStoresFile(File f) {
		List<Store> stores = new ArrayList<Store>();
		try (Scanner s = new Scanner(f)) {
			int n = Integer.parseInt(s.nextLine());
			for (int i = 0; i < n; i++) {
				String line = s.nextLine();
				if (!line.trim().isEmpty()) {
					Store e = null;
					String tokens[] = line.split(",");
					String storeCode = tokens[0];
					String managerCode = tokens[1];
					String street = tokens[2];
					String city = tokens[3];
					String state = tokens[4];
					String zipCode = tokens[5];
					String country = tokens[6];
					e = new Store(storeCode, managerCode, street, city, state, zipCode, country);
					stores.add(e);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			System.exit(0);
		}
		return stores;
	}

	// loads person data
	public static List<Person> loadPeopleFile(File a) {
		List<Person> person = new ArrayList<>();
		try {
			Scanner s = new Scanner(a);
			int n = Integer.parseInt(s.nextLine());
			for (int i = 0; i < n; i++) {
				String line = s.nextLine();
				String tokens[] = line.split(",");
				String personCode = tokens[0];
				String lastName = tokens[1];
				String firstName = tokens[2];
				String street = tokens[3];
				String city = tokens[4];
				String state = tokens[5];
				String zipCode = tokens[6];
				String country = tokens[7];
				List<String> emails = new ArrayList<>();
				for (int k = 8; k < tokens.length; k++) {
					emails.add(tokens[k]);
				}
				Person p = new Person(personCode, lastName, firstName, street, city, state, zipCode, country, emails);

				for (int j = 0; j < emails.size(); j++) {
					p.addEmail(emails.get(j));
				}
				person.add(p);
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			System.exit(0);
		}

		return person;
	}

	// loads item data
	public static List<Item> loadItemsFile(File b) {
		List<Item> Items = new ArrayList<Item>();
		try (Scanner s = new Scanner(b)) {
			int n = Integer.parseInt(s.nextLine());
			for (int i = 0; i < n; i++) {
				String line = s.nextLine();
				if (!line.trim().isEmpty()) {
					Item e = null;
					String tokens[] = line.split(",");
					String code = tokens[0];
					String type = tokens[1];
					if (tokens[1].equals("E")) {
						String name = tokens[2];
						String model = tokens[3];
						e = new Equipment(code, type, name, null, model);
					} else if (tokens[1].equals("P")) {
						String name = tokens[2];
						String unit = tokens[3];
						Double unitPrice = Double.parseDouble(tokens[4]);
						e = new Product(code, type, name, null, unit, unitPrice);
					} else if (tokens[1].equals("S")) {
						String name = tokens[2];
						Double hourlyRate = Double.parseDouble(tokens[3]);
						e = new Service(code, type, name, null, hourlyRate);
					}

					Items.add(e);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return Items;
	}

//	static Map<String, Set<Invoice>> invoiceMap = new HashMap<>();

	public static List<Invoice> loadInvoicesFile(File g) {
		List<Invoice> Invoices = new ArrayList<Invoice>();

		try (Scanner s = new Scanner(g)) {
			int n = Integer.parseInt(s.nextLine());
			for (int i = 0; i < n; i++) {
				String line = s.nextLine();
				if (!line.trim().isEmpty()) {
					Invoice e = null;
					String tokens[] = line.split(",");
					String invoiceCode = tokens[0];
					String storeCode = tokens[1];
					List<Store> stores = loadStoresFile(storesCSV);
					Store invoiceStore = null;
					for (Store store : stores) {
						if (store.getStoreCode().equals(storeCode)) {
							invoiceStore = store;
						}
					}
					String customerCode = tokens[2];
					List<Person> persons = loadPeopleFile(personsCSV);
					Person invoiceCustomer = null;
					for (Person person : persons) {
						if (person.getPersonCode().equals(customerCode)) {
							invoiceCustomer = person;
						}
					}
					String salesPersonCode = tokens[3];
					Person invoiceSalesPerson = null;
					for (Person person : persons) {
						if (person.getPersonCode().equals(salesPersonCode)) {
							invoiceSalesPerson = person;
						}
					}
					String date = tokens[4];
					LocalDate date2 = LocalDate.parse(date);
					e = new Invoice(invoiceCode, invoiceStore, invoiceCustomer, invoiceSalesPerson, date2);
//					Set<Invoice> thisStoreInvoices = invoiceMap.get(storeCode);
//					if (thisStoreInvoices == null) {
//						thisStoreInvoices = new HashSet<>();
//						invoiceMap.put(storeCode, thisStoreInvoices);
//					}
//					thisStoreInvoices.add(e);
					Invoices.add(e);
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			System.exit(0);
		}
//		for (Set<Invoice> v : invoiceMap.values()) {
//			System.out.println(v.toString());
//		}
		return Invoices;
	}

//	static HashMap<String, Set<Item>> itemMap = new HashMap<String, Set<Item>>();

	public static List<Item> loadInvoiceItemsFile(File f) {
		List<Item> invoiceItems = new ArrayList<Item>();
		try (Scanner s = new Scanner(f)) {
			int n = Integer.parseInt(s.nextLine());
			for (int i = 0; i < n; i++) {
				String line = s.nextLine();
				if (!line.trim().isEmpty()) {
					Item e = null;
					String tokens[] = line.split(",");
					String invoiceCode = tokens[0];
					String itemCode = tokens[1];
					List<Item> items = loadItemsFile(itemsCSV);
					for (Item item : items) {
						if (item.getCode().equals(itemCode)) {
							if (item.getType().equals("P")) {
								double quanOrHours = Double.parseDouble(tokens[2]);
								item.setInvoiceCode(invoiceCode);
								e = new Product((Product) item, quanOrHours);
							}
							if (item.getType().equals("S")) {
								double quanOrHours = Double.parseDouble(tokens[2]);
								item.setInvoiceCode(invoiceCode);
								e = new Service((Service) item, quanOrHours);
							}
							if (item.getType().equals("E")) {
								if (tokens[2].equals("P")) {
									String purOrLease = tokens[2];
									double price = Double.parseDouble(tokens[3]);
									item.setInvoiceCode(invoiceCode);
									e = new Purchased((Equipment) item, purOrLease, price);
								}
								if (tokens[2].equals("L")) {
									String purOrLease = tokens[2];
									double fee = Double.parseDouble(tokens[3]);
									String startDate = tokens[4];
									String endDate = tokens[5];
									item.setInvoiceCode(invoiceCode);
									e = new Leased((Equipment) item, purOrLease, fee, startDate, endDate);
								}
							}
						}
					}
					invoiceItems.add(e);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			System.exit(0);
		}
		return invoiceItems;
	}

	public static void printPerson() {
		List<Person> person = loadPeopleFile(personsCSV);
		XStream xstream = new XStream(new DomDriver());
		try {
			FileWriter writerX = new FileWriter("data/Persons.xml");
			FileWriter writerJ = new FileWriter("data/Persons.json");
			for (Person p : person) {
				String xml = xstream.toXML(p);
				writerX.write(xml);

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				gson.toJson(p, writerJ);
			}
			writerX.close();
			writerJ.close();
		} catch (IOException e) {
			System.out.println("error");
		}
	}

	public static void printStore() {
		List<Store> stores = loadStoresFile(storesCSV);
		System.out.println("yes");
		XStream xstream = new XStream(new DomDriver());
		try {
			FileWriter writerX = new FileWriter("data/Stores.xml");
			FileWriter writerJ = new FileWriter("data/Stores.json");
			for (Store s : stores) {
				String xml = xstream.toXML(s);
				writerX.write(xml);

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				gson.toJson(s, writerJ);
			}
			writerX.close();
			writerJ.close();
		} catch (IOException e) {
			System.out.println("error");
		}
	}

	public static void printItems() {
		List<Item> items = loadItemsFile(itemsCSV);
		XStream xstream = new XStream(new DomDriver());
		try {
			FileWriter writerX = new FileWriter("data/Items.xml");
			FileWriter writerJ = new FileWriter("data/Items.json");
			for (Item i : items) {
				String xml = xstream.toXML(i);
				writerX.write(xml);

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				gson.toJson(i, writerJ);
			}
			writerX.close();
			writerJ.close();
		} catch (IOException e) {
			System.out.println("error");
		}
	}

	public static void main(String[] args) {
		loadStoresFile((storesCSV));
	}
}
