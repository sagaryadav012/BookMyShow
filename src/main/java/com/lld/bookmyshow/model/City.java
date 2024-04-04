package com.lld.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "cities")
public class City extends BaseModel{
    private String name;

    @OneToMany(mappedBy = "city")
    private List<Theatre> theatres;
}
