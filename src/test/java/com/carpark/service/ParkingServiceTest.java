package com.carpark.service;

import com.carpark.exception.ParkingFullException;
import com.carpark.model.Car;
import com.carpark.model.Motorbike;
import com.carpark.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingServiceTest {

    private ParkingService parkingService;

    @BeforeEach
    void setUp() {
        parkingService = new ParkingService(2);
    }

    @Test
    void testParkAndUnpark() throws ParkingFullException {
        int slotNumber = parkingService.park(new Car("CAR-001"));
        assertEquals(1, slotNumber);

        Ticket ticket = parkingService.unpark(slotNumber);
        assertEquals("CAR-001", ticket.registrationNumber());
        assertEquals(1, ticket.slotNumber());
        assertTrue(ticket.fee() > 0);
    }

    @Test
    void testParkingFull() throws ParkingFullException {
        parkingService.park(new Car("CAR-001"));
        parkingService.park(new Motorbike("MB-001"));
        assertThrows(ParkingFullException.class, () -> parkingService.park(new Car("CAR-002")));
    }

    @Test
    void testUnparkEmptySlot() {
        assertThrows(IllegalArgumentException.class, () -> parkingService.unpark(1));
    }

    @Test
    void testInvalidSlotNumber() {
        assertThrows(IllegalArgumentException.class, () -> parkingService.unpark(0));
        assertThrows(IllegalArgumentException.class, () -> parkingService.unpark(3));
    }
}
