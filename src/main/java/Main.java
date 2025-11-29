import com.carpark.exception.ParkingFullException;
import com.carpark.model.Car;
import com.carpark.model.Motorbike;
import com.carpark.service.ParkingService;

import java.util.List;
import java.util.Scanner;

/**
 * Constant for parking capacity.
 * Java convention: constants are static final with UPPER_SNAKE_CASE naming.
 */
private static final int PARKING_CAPACITY = 10;

/**
 * Main class demonstrating INSTANCE MAIN METHOD (JEP 512 - Java 25).
 *
 * Instead of the traditional: public static void main(String[] args)
 * We can now use: void main()
 *
 * This simplifies the entry point and allows instance methods to be called directly.
 * Requires --enable-preview flag to compile and run.
 */
void main() {
    ParkingService parkingService = new ParkingService(PARKING_CAPACITY);
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

private void parkVehicle(Scanner scanner, ParkingService parkingService) {
    System.out.print("Enter vehicle type (CAR/MOTORBIKE or C/MC): ");
    String typeInput = scanner.nextLine();
    // LVTI (Local Variable Type Inference) - 'var' infers the type from the right-hand side
    var type = getVehicleTypeFromInput(typeInput);

    if (type == null) {
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
    } catch (ParkingFullException | IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}

private void unparkVehicle(Scanner scanner, ParkingService parkingService) {
    System.out.print("Enter slot number to unpark: ");
    int slotNumber = scanner.nextInt();
    scanner.nextLine(); // consume newline

    try {
        // LVTI - 'var' infers type as Ticket from unpark() return type
        var ticket = parkingService.unpark(slotNumber);
        System.out.println("Vehicle unparked. Fee: " + ticket.fee());
        System.out.println("Ticket details: " + ticket);
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}

/**
 * Demonstrates LAMBDAS with PREDICATE, PATTERN MATCHING, and METHOD REFERENCES.
 *
 * PATTERN MATCHING: 'v instanceof Car car' both checks the type AND
 * creates a variable 'car' of type Car in one expression.
 *
 * METHOD REFERENCE: 'System.out::println' is shorthand for 'x -> System.out.println(x)'
 */
private void findVehicles(Scanner scanner, ParkingService parkingService) {
    System.out.print("Enter vehicle type to find (CAR/MOTORBIKE or C/MC): ");
    String typeInput = scanner.nextLine();
    String type = getVehicleTypeFromInput(typeInput);

    if (type == null) {
        System.out.println("Invalid vehicle type.");
        return;
    }

    List<com.carpark.model.ParkingSlot> foundSlots;
    if ("CAR".equalsIgnoreCase(type)) {
        // LAMBDA with PATTERN MATCHING: 'v instanceof Car car' checks type AND creates typed variable
        foundSlots = parkingService.findVehicles(v -> v instanceof Car car && car.getType() == com.carpark.model.VehicleType.CAR);
    } else if ("MOTORBIKE".equalsIgnoreCase(type)) {
        // LAMBDA with PATTERN MATCHING for Motorbike
        foundSlots = parkingService.findVehicles(v -> v instanceof Motorbike motorbike && motorbike.getType() == com.carpark.model.VehicleType.MOTORBIKE);
    } else {
        System.out.println("Invalid vehicle type.");
        return;
    }

    if (foundSlots.isEmpty()) {
        System.out.println("No vehicles of type " + type + " found.");
    } else {
        System.out.println("Found vehicles of type " + type + ":");
        // METHOD REFERENCE: System.out::println is equivalent to slot -> System.out.println(slot)
        foundSlots.forEach(slot -> System.out.println("Slot " + slot.getSlotNumber() + ": " + slot.getVehicle().getRegistrationNumber()));
    }
}

private void viewSpecificSlots(Scanner scanner, ParkingService parkingService) {
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

/**
 * Demonstrates SWITCH EXPRESSION
 *
 * SWITCH EXPRESSION vs SWITCH STATEMENT:
 * - Switch EXPRESSION returns a value (assigned to variable or returned)
 * - Uses arrow syntax (->) instead of colon (:)
 * - No fall-through, no break needed
 * - Must be exhaustive (all cases covered or have default)
 */
private String getVehicleTypeFromInput(String input) {
    return switch (input.toLowerCase()) {
        case "car", "c" -> "CAR";
        case "motorbike", "mc" -> "MOTORBIKE";
        default -> null;
    };
}
