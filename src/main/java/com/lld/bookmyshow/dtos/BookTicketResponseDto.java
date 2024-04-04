package com.lld.bookmyshow.dtos;

import com.lld.bookmyshow.model.Ticket;
import lombok.Data;

@Data
public class BookTicketResponseDto {
    private Ticket ticket;
    private Response response;
}
