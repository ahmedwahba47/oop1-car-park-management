package com.carpark.model;

public interface Parkable {
    
    // Abstract method
    String getRegistrationNumber();
    
    // Static method
    static String getParkingInfo() {
        return "This is a parkable vehicle.";
    }

    // Default method
    default void printRegistration() {
        System.out.println("Registration: " + getRegistrationNumber());
        printVin();
    }

    // Private method
    private void printVin() {
        System.out.println("VIN: " + generateVin());
    }

    // Private method
    private String generateVin() {
        return "VIN-" + getRegistrationNumber();
    }
}
