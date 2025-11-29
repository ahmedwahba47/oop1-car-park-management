package com.carpark.service;

import com.carpark.exception.ParkingFullException;
import com.carpark.model.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class ParkingService {
    private final ParkingSlot[] slots;
    private final List<Ticket> ticketHistory = new ArrayList<>();
    private final Set<String> parkedRegistrationNumbers = new HashSet<>();

    public ParkingService(int capacity) {
        this.slots = new ParkingSlot[capacity];
        for (int i = 0; i < capacity; i++) {
            slots[i] = new ParkingSlot(i + 1);
        }
    }

    public int park(Vehicle vehicle) throws ParkingFullException {
        if (parkedRegistrationNumbers.contains(vehicle.getRegistrationNumber())) {
            throw new IllegalArgumentException("Vehicle with registration number " + vehicle.getRegistrationNumber() + " is already parked.");
        }

        for (ParkingSlot slot : slots) {
            if (slot.isAvailable()) {
                slot.park(vehicle);
                parkedRegistrationNumbers.add(vehicle.getRegistrationNumber());
                return slot.getSlotNumber();
            }
        }
        throw new ParkingFullException("Car park is full.");
    }

    /**
     * Unparks a vehicle from a slot and returns a ticket.
     * This method demonstrates call-by-value for primitive types (slotNumber)
     * and object references (the returned Ticket).
     * The returned Ticket is a record, which is immutable, so no defensive copy is needed.
     */
    public Ticket unpark(int slotNumber) {
        if (slotNumber < 1 || slotNumber > slots.length) {
            throw new IllegalArgumentException("Invalid slot number.");
        }
        ParkingSlot slot = slots[slotNumber - 1];
        if (slot.isAvailable()) {
            throw new IllegalArgumentException("Slot is already empty.");
        }
        Vehicle vehicle = slot.getVehicle();
        LocalDateTime entryTime = slot.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();
        long duration = Duration.between(entryTime, exitTime).toHours();
        if (duration == 0) {
            duration = 1; // Minimum 1 hour fee
        }
        Money fee = vehicle.calculateFee(duration);
        slot.unpark();
        parkedRegistrationNumbers.remove(vehicle.getRegistrationNumber());
        Ticket ticket = new Ticket(vehicle.getRegistrationNumber(), slotNumber, entryTime, exitTime, fee);
        ticketHistory.add(ticket);
        return ticket;
    }

    /**
     * Displays the status of all parking slots.
     * This method uses a StringBuilder to efficiently build the status string.
     */
    public void displayParkingStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parking Status:\n");
        for (ParkingSlot slot : slots) {
            String status = slot.isAvailable() ? "Available" : "Occupied by " + slot.getVehicle().getType() + " (" + slot.getVehicle().getRegistrationNumber() + ")";
            sb.append("Slot ").append(slot.getSlotNumber()).append(": ").append(status).append("\n");
        }
        System.out.println(sb.toString());
    }

    /**
     * Finds vehicles based on a predicate.
     * The predicate is a lambda expression, which is a closure.
     *
     * FINAL vs EFFECTIVELY FINAL:
     * - 'final' variables are explicitly declared with the final keyword
     * - 'effectively final' variables are never reassigned after initialization
     * - Lambdas can only capture local variables that are final or effectively final
     * - In the example below, 'slot' and 'vehicle' are effectively final because
     *   they are never reassigned within their scope, allowing them to be used in lambdas.
     */
    public List<ParkingSlot> findVehicles(Predicate<Vehicle> predicate) {
        List<ParkingSlot> foundSlots = new ArrayList<>();
        for (ParkingSlot slot : slots) {
            if (!slot.isAvailable()) {
                Vehicle vehicle = slot.getVehicle();
                if (predicate.test(vehicle)) {
                    foundSlots.add(slot);
                }
            }
        }
        return foundSlots;
    }
    
    // Overloaded park method
    public int park(String registrationNumber, VehicleType type) throws ParkingFullException {
        Vehicle vehicle = switch (type) {
            case CAR -> new Car(registrationNumber);
            case MOTORBIKE -> new Motorbike(registrationNumber);
        };
        return park(vehicle);
    }

    /**
     * Prints the details of multiple slots using varargs.
     */
    public void printSlotDetails(int... slotNumbers) {
        System.out.println("Details for selected slots:");
        for (int slotNumber : slotNumbers) {
            if (slotNumber >= 1 && slotNumber <= slots.length) {
                ParkingSlot slot = slots[slotNumber - 1];
                String status = slot.isAvailable() ? "Available" : "Occupied by " + slot.getVehicle().getType() + " (" + slot.getVehicle().getRegistrationNumber() + ")";
                System.out.println("Slot " + slot.getSlotNumber() + ": " + status);
            } else {
                System.out.println("Slot " + slotNumber + ": Invalid slot number.");
            }
        }
    }
}
