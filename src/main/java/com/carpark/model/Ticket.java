package com.carpark.model;

import java.time.LocalDateTime;

/**
 * A Java Record representing a parking ticket.
 *
 * RECORDS automatically provide:
 * - A canonical constructor
 * - Getter methods for all components (registrationNumber(), slotNumber(), etc.)
 * - equals() and hashCode() implementations
 * - A toString() implementation (which we override below)
 *
 * Records are IMPLICITLY FINAL and IMMUTABLE - all fields are final.
 * This makes defensive copying unnecessary when returning Ticket objects.
 */
public record Ticket(String registrationNumber, int slotNumber, LocalDateTime entryTime, LocalDateTime exitTime, Money fee) {
    @Override
    public String toString() {
        return "Ticket[" +
               "registrationNumber=" + registrationNumber + ", " +
               "slotNumber=" + slotNumber + ", " +
               "entryTime=" + entryTime.withNano(0) + ", " +
               "exitTime=" + exitTime.withNano(0) + ", " +
               "fee=" + fee +
               "]";
    }
}
