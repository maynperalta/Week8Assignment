package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	private List<Automobile> inventory = new ArrayList<>();
	private static final String FILE_PATH = "";

	public static void main(String[] args) {
		Main app = new Main();
		app.run();
	}
// Switch case for menu
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
						saveOnExit(scnr);
						System.out.println("Exiting program.");
						running = false;
					}
					default -> System.out.println("Invalid selection. Try again.");				
				}
			}
		} catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
// Method to add automobile	
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
			int mileage = Integer.parseInt(scnr.nextLine());
			System.out.print("Enter vehicle MSRP: ");
			int price = Integer.parseInt(scnr.nextLine());
			System.out.print("Is vehicle available (true/false): ");
			boolean isAvailable = Boolean.parseBoolean(scnr.nextLine());
			
			Automobile vehicle = new Automobile(vehicleId, make, model, vin, color, drivetrain, category, year, mileage, price, isAvailable);
			inventory.add(vehicle);
			System.out.println("Vehicle added to inventory.");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
// Method to display dealer inventory
	private void displayInventory() {
		try {
			if (inventory.isEmpty()) {
				System.out.println("Inventory currently empty.");
				return;
			}
			System.out.println("Current Inventory:");
			for (Automobile vehicle : inventory) {
				System.out.println(vehicle.stringFormat());
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
// Method to update automobile data
	private void updateAutomobile(Scanner scnr) {
		try {
			System.out.print("Enter ID of vehicle to update: ");
			String vehicleId = scnr.nextLine();
			Automobile vehicle = findVehicle(vehicleId);
			if (vehicle == null) {
				System.out.println("ID not found.");
				return;
			}
			System.out.print("Update make: ");
			String make = scnr.nextLine();
			System.out.print("Update model: ");
			String model = scnr.nextLine();
			System.out.print("Update VIN: ");
			String vin = scnr.nextLine();
			System.out.print("Update color: ");
			String color = scnr.nextLine();
			System.out.print("Update drivetrain: ");
			String drivetrain = scnr.nextLine();
			System.out.print("Update category: ");
			String category = scnr.nextLine();
			System.out.print("Update year: ");
			int year = Integer.parseInt(scnr.nextLine());
			System.out.print("Update mileage: ");
			int mileage = Integer.parseInt(scnr.nextLine());
			System.out.print("Update price: ");
			int price = Integer.parseInt(scnr.nextLine());
			System.out.print("Is vehicle available (true/false): ");
			boolean isAvailable = Boolean.parseBoolean(scnr.nextLine());
			
			System.out.println(vehicle.addAutomobile(vehicleId, make, model, vin, color, drivetrain, category, year, mileage, price, isAvailable));
			System.out.println("Vehicle updated.");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
// Method to clear automobile data
	private void deleteAutomobile(Scanner scnr) {
		try {
			System.out.print("Enter ID of vehicle to delete: ");
			String vehicleId = scnr.nextLine();
			Automobile vehicle = findVehicle(vehicleId);
			if (vehicle != null) {
				System.out.println(vehicle.deleteAutomobile());
				inventory.remove(vehicle);
			} else {
				System.out.println("Vehicle ID not found.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
// Find automobile	
	private Automobile findVehicle(String vehicleId) {
		for (Automobile vehicle : inventory) {
			if (vehicle.getAutomobile().equalsIgnoreCase(vehicleId)) {
				return vehicle; 
			}
		}
		return null;
	}
// File writing and reading
	private void saveToFile() {
		try (FileWriter writer = new FileWriter(FILE_PATH)) {
			for (Automobile vehicle : inventory) {
				String[] data = vehicle.displayAutomobile();
				writer.write(String.join(",", data) + "\n");
			}
			System.out.println("Inventory saved.");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	private void loadFromFile() {
		File file = new File(FILE_PATH);
		if(!file.exists()) return;
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 11) {
					Automobile vehicle = new Automobile(parts[0], parts[2], parts[3], parts[9], parts[4], 
							parts[5], parts[6], Integer.parseInt(parts[1]), Integer.parseInt(parts[7]),
							Integer.parseInt(parts[8]), Boolean.parseBoolean(parts[10]));
					inventory.add(vehicle);
				}
			}
			System.out.println("Loaded " + inventory.size() + " vehicle(s) from file.");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
// Ask user to save when exiting
	private void saveOnExit(Scanner scnr) {
		boolean inputValid = false;
		
		while(!inputValid) {
			System.out.println("Do you wish to save inventory data (Y/N)?: ");
			String userChoice = scnr.nextLine();
			if (userChoice.equalsIgnoreCase("Y")) {
				saveToFile();
				inputValid = true;
			} else if (userChoice.equalsIgnoreCase("N")) {
				System.out.println("Inventory not saved.");
				inputValid = true;
			} else {
				System.out.println("Invalid input.");
			}
		}
	}
}