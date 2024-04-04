package com.lld.bookmyshow.services;

import com.lld.bookmyshow.Exceptions.InvalidBookTicketRequestException;
import com.lld.bookmyshow.Exceptions.SeatsUnavailableException;
import com.lld.bookmyshow.model.*;
import com.lld.bookmyshow.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{
    UserService userService;
    ShowService showService;
    ShowSeatService showSeatService;

    TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(UserService userService, ShowService showService, ShowSeatService showSeatService, TicketRepository ticketRepository) {
        this.userService = userService;
        this.showService = showService;
        this.showSeatService = showSeatService;
        this.ticketRepository = ticketRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Ticket bookTicket(int userId, int showId, List<Integer> showSeatIds) throws Exception{
        /*
            1. Check user id is valid.
            2. Check showId and showId in showSeatIds should match.
            3. Start Transaction(Serializable)
            4. select * from show_seats where id in (showSeatIds) and seat_status = 'AVAILABLE' and show_id = {{show_id}} for update.
            5. If all seats are not available
            6. Throw an error and rollback the transaction
            7. Update show_seats set seat_status = "BLOCKED" where ids in (showSeatIds)
            8. Generate Ticket object.
            9. Store and return ticket object.
         */

        Optional<User> userById = userService.findById(userId);
        User user;
        if(userById.isPresent()){
            user = userById.get();
        }
        else{
            throw new InvalidBookTicketRequestException("Not valid user");
        }

        Show show = showService.findById(showId).orElseThrow(() -> new InvalidBookTicketRequestException("Show id is not valid"));
        ShowSeat showSeat = showSeatService.findById(showSeatIds.get(0)).orElseThrow(() -> new InvalidBookTicketRequestException("Seat Id is invalid"));

        if(showSeat.getShow().getId() != showId){
            throw new InvalidBookTicketRequestException("selected seats dont belong to the same show");
        }
        List<ShowSeat> showSeats = showSeatService.findShowSeatByIdInAndSeatStatus_AvailableAndShow(showSeatIds, show);

        if(showSeats.size() != showSeatIds.size()){
            throw new SeatsUnavailableException("Some of the seats you are trying to book are unavailable");
        }
        for(ShowSeat ss : showSeats){
            ss.setBookedBy(user);
            ss.setSeatStatus(SeatStatus.BLOCKED);
        }
        showSeatService.saveAll(showSeats);

        Ticket ticket = new Ticket();
        ticket.setMovie(show.getMovie());
        ticket.setShow(show);
        ticket.setUser(user);
        ticket.setShowSeats(showSeats);

        return ticketRepository.save(ticket);
    }
}
