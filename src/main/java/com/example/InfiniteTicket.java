package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InfiniteTicket extends Ticket {
    private LocalDate expirationDate;

    public InfiniteTicket(LocalDate expirationDate, TicketType type) {
        super(Integer.MAX_VALUE, type); // Используем максимальное значение, чтобы обозначить "бесконечные" поездки
        this.expirationDate = expirationDate;
        this.setLastUsed(null);
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "InfiniteTicket{" +
                "expirationDate=" + expirationDate +
                ", type=" + getType() +
                ", last used=" + getLastUsed() +
                '}';
    }
}
