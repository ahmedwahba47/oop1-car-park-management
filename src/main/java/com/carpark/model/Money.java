package com.carpark.model;

import java.math.BigDecimal;

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

    @Override
    public String toString() {
        return amount.toString();
    }
}
