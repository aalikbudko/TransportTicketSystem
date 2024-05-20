package com.example;

import java.time.LocalDateTime;

//класс, представляющий билет
class Ticket {
    private int trips;
    private TicketType type;
    private LocalDateTime lastUsed;

    public Ticket(int trips, TicketType type) {
        this.trips = trips;
        this.type = type;
        this.lastUsed = null;
    }

    public int getTrips() {
        return trips;
    }

    public void setTrips(int trips) {
        this.trips = trips;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public LocalDateTime getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(LocalDateTime lastUsed) {
        this.lastUsed = lastUsed;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "trips=" + trips +
                ", type=" + type +
                ", last used=" + lastUsed +
                '}';
    }
}
