package com.lld.bookmyshow.services;

import com.lld.bookmyshow.model.Show;

import java.util.Optional;

public interface ShowService {
    Optional<Show> findById(int showId);
}
