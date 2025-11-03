package com.carpark.model;

import java.time.LocalDateTime;

public record Ticket(String registrationNumber, int slotNumber, LocalDateTime entryTime, LocalDateTime exitTime, double fee) {
}
