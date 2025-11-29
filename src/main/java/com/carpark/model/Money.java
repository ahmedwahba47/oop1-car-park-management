package com.carpark.model;

import java.math.BigDecimal;

/**
 * A custom immutable type representing monetary values.
 *
 * IMMUTABILITY is achieved through:
 * 1. The class is declared 'final' - cannot be subclassed
 * 2. The field 'amount' is declared 'final' - cannot be reassigned
 * 3. No setter methods exist
 * 4. Methods like add() return NEW Money instances instead of modifying this one
 * 5. BigDecimal itself is immutable
 */
public final class Money {
    private final BigDecimal amount;

    public Money(String amount) {
        this.amount = new BigDecimal(amount);
    }

    public Money(double amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount).doubleValue());
    }

    public double doubleValue() {
        return amount.doubleValue();
    }

    @Override
    public String toString() {
        return amount.toString();
    }
}
