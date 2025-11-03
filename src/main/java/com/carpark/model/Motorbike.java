package com.carpark.model;

public final class Motorbike extends Vehicle {
    public Motorbike(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.isBlank()) {
            throw new IllegalArgumentException("Registration number cannot be null or blank.");
        }
        super(registrationNumber, VehicleType.MOTORBIKE);
    }

    @Override
    public double calculateFee(long durationInHours) {
        return durationInHours * 1.5;
    }
}
