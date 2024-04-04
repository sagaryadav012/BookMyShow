package com.lld.bookmyshow.controllers;

import com.lld.bookmyshow.Exceptions.InvalidBookTicketRequestException;
import com.lld.bookmyshow.dtos.BookTicketRequestDto;
import com.lld.bookmyshow.dtos.BookTicketResponseDto;
import com.lld.bookmyshow.dtos.Response;
import com.lld.bookmyshow.model.Ticket;
import com.lld.bookmyshow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketController {
    TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(path = "/bookTicket")
    public BookTicketResponseDto bookTicket(BookTicketRequestDto requestDto){
        BookTicketResponseDto responseDto = new BookTicketResponseDto();
        try {
            validateBookTicketRequest(requestDto);
            Ticket ticket = ticketService.bookTicket(requestDto.getUserId(), requestDto.getShowId(), requestDto.getShowSeatIds());
            responseDto.setTicket(ticket);
            responseDto.setResponse(Response.getSuccessResponse());
        } catch (Exception e) {
            Response response = Response.getFailureResponse(e.getMessage());
            responseDto.setResponse(response);
        }
        return responseDto;
    }
    public void validateBookTicketRequest(BookTicketRequestDto requestDto) throws InvalidBookTicketRequestException{

        if(requestDto.getUserId() <= 0){
            throw new InvalidBookTicketRequestException("User id cannot be negative or zero");
        }
        if(requestDto.getShowId() <= 0){
            throw new InvalidBookTicketRequestException("Show id cannot be negative or zero");
        }
        if(requestDto.getShowSeatIds() == null || requestDto.getShowSeatIds().isEmpty()){
            throw new InvalidBookTicketRequestException("Show ids should be present");
        }

    }
}
