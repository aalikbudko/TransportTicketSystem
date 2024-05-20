package com.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TicketService service = new TicketService();

        // Пример использования с обычным билетом для метро
        Ticket metroTicket = new Ticket(3, TicketType.METRO);
        try {
            metroTicket = service.checkAndDeductTrip(metroTicket, TicketType.METRO);
            System.out.println("Ticket after deduction: " + metroTicket);
        } catch (NoTripsException | ExpiredTicketException | InvalidTicketTypeException e) {
            System.out.println(e.getMessage());
        }

        // Пример использования с бесконечным билетом для обоих видов транспорта
        InfiniteTicket infiniteTicket = new InfiniteTicket(LocalDate.now().plusDays(1), TicketType.BOTH);
        try {
            infiniteTicket = (InfiniteTicket) service.checkAndDeductTrip(infiniteTicket, TicketType.GROUND);
            System.out.println("Infinite ticket is still valid: " + infiniteTicket);
        } catch (NoTripsException | ExpiredTicketException | InvalidTicketTypeException e) {
            System.out.println(e.getMessage());
        }
    }
}

