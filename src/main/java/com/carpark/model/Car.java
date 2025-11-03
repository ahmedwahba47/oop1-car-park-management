package com.carpark.model;

public final class Car extends Vehicle {
    public Car(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.isBlank()) {
            throw new IllegalArgumentException("Registration number cannot be null or blank.");
        }
        super(registrationNumber, VehicleType.CAR);
    }

    @Override
    public double calculateFee(long durationInHours) {
        return durationInHours * 2.5;
    }
}
