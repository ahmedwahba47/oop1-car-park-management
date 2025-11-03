package com.carpark.model;

public interface Parkable {
    // Static method
    static String getParkingInfo() {
        return "This is a parkable vehicle.";
    }

    // Default method
    default void printRegistration() {
        System.out.println("Registration: " + getRegistrationNumber());
        printVin();
    }

    // Private method (from Java 9)
    private void printVin() {
        System.out.println("VIN: " + generateVin());
    }

    String getRegistrationNumber();

    // Private method
    private String generateVin() {
        return "VIN-" + getRegistrationNumber();
    }
}
