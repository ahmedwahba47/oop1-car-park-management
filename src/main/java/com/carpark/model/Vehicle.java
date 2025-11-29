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
     * Constructor for Vehicle with both parameters.
     * 'this.registrationNumber' is used to distinguish the instance variable from the parameter.
     */
    public Vehicle(String registrationNumber, VehicleType type) {
        this.registrationNumber = registrationNumber;
        this.type = type;
    }

    /**
     * Overloaded constructor demonstrating 'this()' constructor chaining.
     * 'this()' calls another constructor in the SAME class.
     * In contrast, 'this.' accesses instance members.
     */
    public Vehicle(String registrationNumber) {
        this(registrationNumber, VehicleType.CAR); // this() calls the other constructor
    }

    @Override
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public abstract Money calculateFee(long durationInHours);
}
