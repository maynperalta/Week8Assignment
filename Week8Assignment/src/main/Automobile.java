package main;

// Private variables (attributes)
public class Automobile {
	private String vehicleId;
	private String make;
	private String model;
	private String vin;
	private String color;
	private String drivetrain;
	private String category;
	private int year;
	private int mileage;
	private int price;
	private boolean isAvailable;
	
// Default and parameterized constructors
	public Automobile() {
		try {
			this.vehicleId = "";
			this.make = "";
			this.model = "";
			this.vin = "";
			this.color = "";
			this.drivetrain = "";
			this.category = "";
			this.year = 0;
			this.mileage = 0;
			this.price = 0;
			this.isAvailable = false;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 			
		}
	}	
	public Automobile(String vehicleId, String make, String model, String vin, String color, 
			String drivetrain, String category, int year, int mileage, int price, boolean isAvailable) {
		try {
			this.vehicleId = vehicleId;
			this.make = make;
			this.model = model;
			this.vin = vin;
			this.color = color;
			this.drivetrain = drivetrain;
			this.category = category;
			this.year = year;
			this.mileage = mileage;
			this.price = price;
			this.isAvailable = isAvailable;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
// Methods
// Add/update automobile
	public String addAutomobile(String vehicleId, String make, String model, String vin, String color, 
			String drivetrain, String category, int year, int mileage, int price, boolean isAvailable) {
		try {
			this.vehicleId = vehicleId;
			this.make = make;
			this.model = model;
			this.vin = vin;
			this.color = color;
			this.drivetrain = drivetrain;
			this.category = category;
			this.year = year;
			this.mileage = mileage;
			this.price = price;
			this.isAvailable = isAvailable;
			return "Automobile data updated.";
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}
// Convert to strings	
	public String[] displayAutomobile() {
		try {
			return new String[] { vehicleId, String.valueOf(year), make, model, color, drivetrain, 
					category, String.valueOf(mileage), String.valueOf(price), vin, String.valueOf(isAvailable) };
		} catch (Exception e) {
			return new String[] { "Error: " + e.getMessage() };
		}
	}
// Delete automobile		
	public String deleteAutomobile() {
		try {
			this.vehicleId = "";
			this.make = "";
			this.model = "";
			this.vin = "";
			this.color = "";
			this.drivetrain = "";
			this.category = "";
			this.year = 0;
			this.mileage = 0;
			this.price = 0;
			this.isAvailable = false;
			return "Automobile data cleared.";
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}
// Display as string
	public String stringFormat() {
		return String.format("ID: %s | %d %s %s | Color: %s | Drivetrain: %s | Category: %s | Mileage: %d | Price $%d | VIN: %s | Available: %b", 
				vehicleId, year, make, model, color, drivetrain, category, mileage, price, vin, isAvailable);
	}
	public String getAutomobile() {
		return vehicleId;
	}
}