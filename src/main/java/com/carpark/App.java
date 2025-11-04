package com.carpark;

import com.carpark.model.Money; // This import is not directly used in App.java but might be needed by other classes
import com.carpark.exception.ParkingFullException;
import com.carpark.model.Car;
import com.carpark.model.Motorbike;
import com.carpark.model.Ticket;
import com.carpark.model.Vehicle;
import com.carpark.service.ParkingService;
import com.carpark.model.VehicleType; // Added for findVehicles method

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main( String[] args ) {
        ParkingService parkingService = new ParkingService(10);
        System.out.println("Welcome to the Car Park Management System!");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n1. Park a vehicle");
                System.out.println("2. Unpark a vehicle");
                System.out.println("3. Display parking status");
                System.out.println("4. Find vehicles by type");
                System.out.println("5. View details of specific slots (Varargs example)");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> parkVehicle(scanner, parkingService);
                    case 2 -> unparkVehicle(scanner, parkingService);
                    case 3 -> parkingService.displayParkingStatus();
                    case 4 -> findVehicles(scanner, parkingService);
                    case 5 -> viewSpecificSlots(scanner, parkingService);
                    case 6 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    private static void parkVehicle(Scanner scanner, ParkingService parkingService) {
        System.out.print("Enter vehicle type (CAR/MOTORBIKE): ");
        String type = scanner.nextLine();

        if (!"CAR".equalsIgnoreCase(type) && !"MOTORBIKE".equalsIgnoreCase(type)) {
            System.out.println("Invalid vehicle type.");
            return;
        }

        System.out.print("Enter registration number: ");
        String regNumber = scanner.nextLine();

        try {
            int slotNumber;
            if ("CAR".equalsIgnoreCase(type)) {
                slotNumber = parkingService.park(new Car(regNumber));
                System.out.println("Car parked at slot " + slotNumber);
            } else { // Motorbike
                slotNumber = parkingService.park(new Motorbike(regNumber));
                System.out.println("Motorbike parked at slot " + slotNumber);
            }
        } catch (ParkingFullException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void unparkVehicle(Scanner scanner, ParkingService parkingService) {
        System.out.print("Enter slot number to unpark: ");
        int slotNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try {
            Ticket ticket = parkingService.unpark(slotNumber);
            System.out.println("Vehicle unparked. Fee: " + ticket.fee());
            System.out.println("Ticket details: " + ticket);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void findVehicles(Scanner scanner, ParkingService parkingService) {
        System.out.print("Enter vehicle type to find (CAR/MOTORBIKE): ");
        String type = scanner.nextLine();

        List<Vehicle> foundVehicles;
        if ("CAR".equalsIgnoreCase(type)) {
            // Pattern matching for instanceof (Java 16+)
            foundVehicles = parkingService.findVehicles(v -> v instanceof Car car && car.getType() == VehicleType.CAR);
        } else if ("MOTORBIKE".equalsIgnoreCase(type)) {
            // Pattern matching for instanceof (Java 16+)
            foundVehicles = parkingService.findVehicles(v -> v instanceof Motorbike motorbike && motorbike.getType() == VehicleType.MOTORBIKE);
        } else {
            System.out.println("Invalid vehicle type.");
            return;
        }

        if (foundVehicles.isEmpty()) {
            System.out.println("No vehicles of type " + type + " found.");
        } else {
            System.out.println("Found vehicles of type " + type + ":");
            foundVehicles.forEach(v -> System.out.println(v.getRegistrationNumber()));
        }
    }

    private static void viewSpecificSlots(Scanner scanner, ParkingService parkingService) {
        System.out.print("Enter slot numbers to view (space-separated): ");
        String line = scanner.nextLine();
        String[] slotStrings = line.split(" ");
        int[] slotNumbers = new int[slotStrings.length];
        for (int i = 0; i < slotStrings.length; i++) {
            try {
                slotNumbers[i] = Integer.parseInt(slotStrings[i]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid slot number entered: " + slotStrings[i]);
                return;
            }
        }
        parkingService.printSlotDetails(slotNumbers);
    }
}