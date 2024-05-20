package com.example;

// сервис для проверки и списания поездок

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;

public class TicketService {
    private static final int FREE_RIDE_MINUTES = 90;

    public Ticket checkAndDeductTrip(Ticket ticket, TicketType transportType) throws NoTripsException, ExpiredTicketException, InvalidTicketTypeException {
        LocalDateTime now = LocalDateTime.now();
        //проверяем правильный ли тип билета используется
        if (!isValidTicketType(ticket, transportType)) {
            throw new InvalidTicketTypeException("Invalid ticket type for the requested transport");
        }
        if (ticket.getLastUsed() != null && Duration.between(ticket.getLastUsed(), now).toMinutes() < FREE_RIDE_MINUTES) {
            return ticket; // не спсываем поездки, если билет используется повторно в течении 90 минут
        }
        //проверка на бесконечный билет и просрочен ли он
        if (ticket instanceof InfiniteTicket) {
            InfiniteTicket infiniteTicket = (InfiniteTicket) ticket;
            if (LocalDate.now().isAfter(infiniteTicket.getExpirationDate())) {
                throw new ExpiredTicketException("The ticket is expired");
            }
            // поездки бесконечные, значит списывать не нужно
        } else {
            //проверка и списание поездки для обычных билетов
            if (ticket.getTrips() <= 0) {
                throw new NoTripsException("No trips available");
            }
            ticket.setTrips(ticket.getTrips() - 1);
        }
        //обновляем время последнего использования
        ticket.setLastUsed(now);
        return ticket;
    }

    private boolean isValidTicketType(Ticket ticket, TicketType transportType) {
        return ticket.getType() == transportType || ticket.getType() == TicketType.BOTH;
    }
}
