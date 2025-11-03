package com.carpark.service;

import com.carpark.exception.ParkingFullException;
import com.carpark.model.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ParkingService {
    private final ParkingSlot[] slots;
    private final List<Ticket> ticketHistory = new ArrayList<>();

    public ParkingService(int capacity) {
        this.slots = new ParkingSlot[capacity];
        for (int i = 0; i < capacity; i++) {
            slots[i] = new ParkingSlot(i + 1);
        }
    }

    public int park(Vehicle vehicle) throws ParkingFullException {
        for (ParkingSlot slot : slots) {
            if (slot.isAvailable()) {
                slot.park(vehicle);
                return slot.getSlotNumber();
            }
        }
        throw new ParkingFullException("Car park is full.");
    }

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
        double fee = vehicle.calculateFee(duration);
        slot.unpark();
        Ticket ticket = new Ticket(vehicle.getRegistrationNumber(), slotNumber, entryTime, exitTime, fee);
        ticketHistory.add(ticket);
        return ticket;
    }

    public void displayParkingStatus() {
        System.out.println("Parking Status:");
        for (ParkingSlot slot : slots) {
            String status = slot.isAvailable() ? "Available" : "Occupied by " + slot.getVehicle().getRegistrationNumber();
            System.out.println("Slot " + slot.getSlotNumber() + ": " + status);
        }
    }

    public List<Vehicle> findVehicles(Predicate<Vehicle> predicate) {
        List<Vehicle> foundVehicles = new ArrayList<>();
        for (ParkingSlot slot : slots) {
            if (!slot.isAvailable()) {
                Vehicle vehicle = slot.getVehicle();
                if (predicate.test(vehicle)) {
                    foundVehicles.add(vehicle);
                }
            }
        }
        return foundVehicles;
    }
    
    // Overloaded park method
    public int park(String registrationNumber, VehicleType type) throws ParkingFullException {
        Vehicle vehicle = switch (type) {
            case CAR -> new Car(registrationNumber);
            case MOTORBIKE -> new Motorbike(registrationNumber);
        };
        return park(vehicle);
    }
}
