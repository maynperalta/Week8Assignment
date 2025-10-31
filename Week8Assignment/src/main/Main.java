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
			System.out.print("Enter vehicle MSRP: ");
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
	private void displayInventory() {
		try {
			if (inventory.isEmpty()) {
				System.out.println("Inventory currenlty empty.");
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
	private void updateAutomobile(Scanner scnr) {
		try {
			System.out.print("Enter ID of vehicle to update: ");
			String vehicleId = scnr.nextLine();
			Automobile exists = findVehicle(vehicleId);
			if (exists == null) {
				System.out.println("No vehicle matches ID.");
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
			double mileage = Double.parseDouble(scnr.nextLine());
			System.out.print("Update price: ");
			double price = Double.parseDouble(scnr.nextLine());
			System.out.print("Is vehicle available (true/false): ");
			boolean isAvailable = Boolean.parseBoolean(scnr.nextLine());
			
			exists.addAutomobile(vehicleId, make, model, vin, color, drivetrain, category, year, mileage, price, isAvailable);
			System.out.println("Vehicle updated.");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	private void deleteAutomobile(Scanner scnr) {
		try {
			System.out.print("Enter ID of vehicle to delete: ");
			String vehicleId = scnr.nextLine();
			
			Automobile vehicle = findVehicle(vehicleId);
			if (vehicle != null) {
				inventory.remove(vehicle);
				System.out.println("Vehicle deleted.");
			} else {
				System.out.println("Vehicle ID not found.");
			}
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	private Automobile findVehicle(String vehicleId) {
		for(Automobile vehicle : inventory) {
			if (vehicle.getVehicleId().equalsIgnoreCase(vehicleId)) {
				return vehicle; 
			}
		}
		return null;
	}
// File writing and reading
	private void saveToFile() {
		try (FileWriter writer = new FileWriter(FILE_PATH)) {
			for (Automobile vehicle : inventory) {
				writer.write(vehicle.toDataString() + "\n");
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
				Automobile vehicle = Automobile.fromDataString(line);
				if (vehicle != null) inventory.add(vehicle);
			}
			System.out.println("Loaded " + inventory.size() + " vehicle(s) from file.");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
