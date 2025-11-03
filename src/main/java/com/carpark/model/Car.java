package com.carpark.model;

import com.carpark.model.Money;

public final class Car extends Vehicle {
        /**
     * Constructor for Car.
     * This constructor uses a flexible constructor body (a Java 25 feature)
     * to validate the registration number before calling the super constructor.
     * 'super()' is used to call the constructor of the parent class (Vehicle).
     * In contrast, 'super.' would be used to access a member of the parent class.
     */
    public Car(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.isBlank()) {
            throw new IllegalArgumentException("Registration number cannot be null or blank.");
        }
        super(registrationNumber, VehicleType.CAR);
    }

    @Override
    public Money calculateFee(long durationInHours) {
        return new Money(durationInHours * 2.5);
    }
}
