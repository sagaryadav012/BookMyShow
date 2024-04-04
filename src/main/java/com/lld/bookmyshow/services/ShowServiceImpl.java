package com.lld.bookmyshow.services;

import com.lld.bookmyshow.model.Show;
import com.lld.bookmyshow.repositories.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService{
    ShowRepository showRepository;
    @Override
    public Optional<Show> findById(int showId) {
        return showRepository.findById(showId);
    }
}
