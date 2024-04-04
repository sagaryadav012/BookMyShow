package com.lld.bookmyshow.services;

import com.lld.bookmyshow.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket bookTicket(int userId, int showId, List<Integer> showSeatIds) throws Exception;
}
