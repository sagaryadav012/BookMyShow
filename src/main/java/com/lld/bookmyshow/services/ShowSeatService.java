package com.lld.bookmyshow.services;

import com.lld.bookmyshow.model.Show;
import com.lld.bookmyshow.model.ShowSeat;

import java.util.List;
import java.util.Optional;

public interface ShowSeatService {
    Optional<ShowSeat> findById(int showSeatId);
    List<ShowSeat> findShowSeatByIdInAndSeatStatus_AvailableAndShow(List<Integer> showSeatIds, Show show);
    void saveAll(List<ShowSeat> showSeats);
}
