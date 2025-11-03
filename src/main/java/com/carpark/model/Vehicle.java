package com.carpark.model;

public sealed abstract class Vehicle permits Car, Motorbike {
    private final String registrationNumber;
    private final VehicleType type;

    public Vehicle(String registrationNumber, VehicleType type) {
        this.registrationNumber = registrationNumber;
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public abstract double calculateFee(long durationInHours);
}
