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
	private double mileage;
	private double price;
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
			this.mileage = 0.0;
			this.price = 0.0;
			this.isAvailable = false;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 			
		}
	}	
	public Automobile(String vehicleId, String make, String model, String vin, String color, String drivetrain, String category, int year, double mileage, double price, boolean isAvailable) {
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
// Add automobile
	public String addAutomobile(String vehicleId, String make, String model, String vin, String color, String drivetrain, String category, int year, double mileage, double price, boolean isAvailable) {
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
	public String stringFormat() {
		return String.format("ID: %s %d %s %s | Color: %s, Drivetrain: %s, Category: %s, Mileage: %.1f, Price: $%.2f, VIN: %s, Available: %s", vehicleId, year, make, model, color, drivetrain, category, mileage, price, vin, (isAvailable ? "Yes" : "No"));
	}
	public String toDataString() {
		return String.join(",", vehicleId, make, model, color, drivetrain, category, vin, String.valueOf(year), String.valueOf(mileage), String.valueOf(price), String.valueOf(isAvailable));
	}
	public static Automobile fromDataString(String line) {
		try {
			String[] parts = line.split(",");
			if(parts.length != 11) return null;
			return new Automobile(parts[0], parts[1], parts[2], parts[6], parts[3], parts[4], parts[5], Integer.parseInt(parts[7]), Double.parseDouble(parts[8]), Double.parseDouble(parts[9]), Boolean.parseBoolean(parts[10]));
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}
	public String getVehicleId() { return vehicleId; }
}