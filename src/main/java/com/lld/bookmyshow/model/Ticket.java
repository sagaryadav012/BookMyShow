package com.lld.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "tickets")
public class Ticket extends BaseModel{
    @ManyToOne
    private Show show;

    @ManyToOne
    private Movie movie;

    @OneToMany(mappedBy = "ticket")
    private List<ShowSeat> showSeats;

    @ManyToOne
    private User user;
}
