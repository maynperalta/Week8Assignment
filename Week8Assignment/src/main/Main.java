package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	private List<Automobile> inventory = new ArrayList<>();
	private static final String FILE_PATH = "";

	public static void main(String[] args) {
		Main app = new AutomobileInventory();
		app.run();
	}

	public void run() {
		loadFromFile();
		try(Scanner scnr = new Scanner(System.in)) {
			boolean running = true;
			
			while (running) {
				System.out.println("Menu:");
				System.out.println("1. Add vehicle");
				System.out.println("2. Display inventory");
				System.out.println("3. Update vehicle");
				System.out.println("4. Delete vehicle");
				System.out.println("5. Save changes");
				System.out.println("6. Exit");
				System.out.println("Select an option: ");
				
				String userSelection = scnr.nextLine();
				
				switch (userSelection) {
					case "1" -> addAutomobile(scnr);
					case "2" -> displayInventory();
					case "3" -> updateAutomobile(scnr);
					case "4" -> deleteAutomobile(scnr);
					case "5" -> saveToFile();
					case "6" -> {
						saveToFile();
						System.out.println("Saving inventory and exiting program.");
						running = false;
					}
					default -> System.out.println("Invalid selection. Try again.");				
				}
			}
		} catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
// Methods to add, view, update, and delete	
	private void addAutomobile(Scanner scnr) {
		try {
			System.out.print("Enter unique vehicle ID: ");
			String vehicleId = scnr.nextLine();
			System.out.print("Enter vehicle make: ");
			String make = scnr.nextLine();
			System.out.print("Enter vehicle model: ");
			String model = scnr.nextLine();
			System.out.print("Enter VIN: ");
			String vin = scnr.nextLine();
			System.out.print("Enter vehicle color: ");
			String color = scnr.nextLine();
			System.out.print("Enter drivetrain type (AWD, RWD, or FWD): ");
			String drivetrain = scnr.nextLine();
			System.out.print("Enter vehicle category (SUV, Sedan, etc): ");
			String category = scnr.nextLine();
			System.out.print("Enter vehicle year: ");
			int year = Integer.parseInt(scnr.nextLine());
			System.out.print("Enter vehicle mileage: ");
			double mileage = Double.parseDouble(scnr.nextLine());
			System.out.print("Enter vehicle MSRP");
			double price = Double.parseDouble(scnr.nextLine());
			System.out.print("Is vehicle available (true/false): ");
			boolean isAvailable = Boolean.parseBoolean(scnr.nextLine());
			
			Automobile vehicle = new Automobile(vehicleId, make, model, vin, color, drivetrain, category, year, mileage, price, isAvailable);
			inventory.add(vehicle);
			System.out.println("Vehicle added to inventory.");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
}
