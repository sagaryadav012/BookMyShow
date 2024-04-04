package com.lld.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "shows")
public class Show extends BaseModel{
    private Date startTime;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Screen screen;

}
