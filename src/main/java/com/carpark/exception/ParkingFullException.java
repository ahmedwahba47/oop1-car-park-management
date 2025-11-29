package com.carpark.exception;

/**
 * A CHECKED EXCEPTION thrown when the car park is full.
 *
 * CHECKED vs UNCHECKED EXCEPTIONS:
 * - CHECKED: Extends Exception (not RuntimeException). Compiler forces callers to handle it.
 * - UNCHECKED: Extends RuntimeException. No compile-time checking required.
 *
 * This class extends Exception, making it a CHECKED exception.
 * Methods that throw this must declare it with 'throws ParkingFullException'.
 * Callers must either catch it or propagate it.
 */
public class ParkingFullException extends Exception {
    public ParkingFullException(String message) {
        super(message);
    }
}
