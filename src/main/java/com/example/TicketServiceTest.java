package com.example;

import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TicketServiceTest {

    @Test(expected = NoTripsException.class)
    public void testNegativeTrips() throws NoTripsException, ExpiredTicketException, InvalidTicketTypeException {
        TicketService service = new TicketService();
        Ticket ticket = new Ticket(-1, TicketType.METRO);
        service.checkAndDeductTrip(ticket, TicketType.METRO);
    }

    @Test(expected = NoTripsException.class)
    public void testZeroTrips() throws NoTripsException, ExpiredTicketException, InvalidTicketTypeException {
        TicketService service = new TicketService();
        Ticket ticket = new Ticket(0, TicketType.METRO);
        service.checkAndDeductTrip(ticket, TicketType.METRO);
    }

    @Test
    public void testNormalTrips() throws ExpiredTicketException, InvalidTicketTypeException {
        TicketService service = new TicketService();
        Ticket ticket = new Ticket(3, TicketType.METRO);
        try {
            ticket = service.checkAndDeductTrip(ticket, TicketType.METRO);
            assertEquals(2, ticket.getTrips());
        } catch (NoTripsException e) {
            fail("NoTripsException was thrown unexpectedly.");
        }
    }

    @Test
    public void testValidInfiniteTicket() throws NoTripsException, ExpiredTicketException, InvalidTicketTypeException {
        TicketService service = new TicketService();
        InfiniteTicket ticket = new InfiniteTicket(LocalDate.now().plusDays(1), TicketType.BOTH);
        ticket = (InfiniteTicket) service.checkAndDeductTrip(ticket, TicketType.METRO);
        assertEquals(LocalDate.now().plusDays(1), ticket.getExpirationDate());
    }

    @Test(expected = ExpiredTicketException.class)
    public void testExpiredInfiniteTicket() throws NoTripsException, ExpiredTicketException, InvalidTicketTypeException {
        TicketService service = new TicketService();
        InfiniteTicket ticket = new InfiniteTicket(LocalDate.now().minusDays(1), TicketType.BOTH);
        service.checkAndDeductTrip(ticket, TicketType.METRO);
    }

    @Test(expected = InvalidTicketTypeException.class)
    public void testInvalidTicketType() throws NoTripsException, ExpiredTicketException, InvalidTicketTypeException {
        TicketService service = new TicketService();
        Ticket ticket = new Ticket(3, TicketType.GROUND);
        service.checkAndDeductTrip(ticket, TicketType.METRO);
    }

    @Test
    public void testReuseWithin90Minutes() throws NoTripsException, ExpiredTicketException, InvalidTicketTypeException {
        TicketService service = new TicketService();
        Ticket ticket = new Ticket(3, TicketType.METRO);
        try {
            ticket.setLastUsed(LocalDateTime.now().minusMinutes(30));
            ticket = service.checkAndDeductTrip(ticket, TicketType.METRO);
            assertEquals(3, ticket.getTrips()); // поездки не должны списаться
        } catch (NoTripsException e) {
            fail("NoTripsException was thrown unexpectedly.");
        }
    }

    @Test
    public void testReuseAfter90Minutes() throws NoTripsException, ExpiredTicketException, InvalidTicketTypeException {
        TicketService service = new TicketService();
        Ticket ticket = new Ticket(3, TicketType.METRO);
        try {
            ticket.setLastUsed(LocalDateTime.now().minusMinutes(91));
            ticket = service.checkAndDeductTrip(ticket, TicketType.METRO);
            assertEquals(2, ticket.getTrips()); // поездка должна списаться
        } catch (NoTripsException e) {
            fail("NoTripsException was thrown unexpectedly.");
        }
    }
}
