package com.carpark.model;

import java.time.LocalDateTime;

public class ParkingSlot {
    private final int slotNumber;
    private Vehicle vehicle;
    private ParkingStatus status;
    private LocalDateTime entryTime;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.status = ParkingStatus.AVAILABLE;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingStatus getStatus() {
        return status;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.status = ParkingStatus.OCCUPIED;
        this.entryTime = LocalDateTime.now();
    }

    public void unpark() {
        this.vehicle = null;
        this.status = ParkingStatus.AVAILABLE;
        this.entryTime = null;
    }

    public boolean isAvailable() {
        return status == ParkingStatus.AVAILABLE;
    }
}
