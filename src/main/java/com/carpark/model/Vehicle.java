package com.carpark.model;

/**
 * A sealed abstract class representing a vehicle.
 * It permits only Car and Motorbike as subclasses.
 * This demonstrates the sealed classes feature.
 */
public sealed abstract class Vehicle implements Parkable permits Car, Motorbike {
    private final String registrationNumber;
    private final VehicleType type;

    /**
     * Constructor for Vehicle.
     * 'this.registrationNumber' is used to distinguish the instance variable from the parameter.
     * In contrast, 'this()' would be used to call another constructor in the same class.
     */
    public Vehicle(String registrationNumber, VehicleType type) {
        this.registrationNumber = registrationNumber;
        this.type = type;
    }

    @Override
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public abstract double calculateFee(long durationInHours);
}
