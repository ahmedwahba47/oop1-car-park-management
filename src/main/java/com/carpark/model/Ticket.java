package com.carpark.model;

import java.time.LocalDateTime;

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
