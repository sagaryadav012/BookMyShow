package com.lld.bookmyshow.services;

import com.lld.bookmyshow.model.Show;
import com.lld.bookmyshow.model.ShowSeat;
import com.lld.bookmyshow.repositories.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowSeatServiceImpl implements ShowSeatService{
    ShowSeatRepository showSeatRepository;
    @Override
    public Optional<ShowSeat> findById(int showSeatId) {
        return showSeatRepository.findById(showSeatId);
    }

    @Override
    public List<ShowSeat> findShowSeatByIdInAndSeatStatus_AvailableAndShow(List<Integer> showSeatIds, Show show) {
        return showSeatRepository.findShowSeatByIdInAndSeatStatus_AvailableAndShow(showSeatIds, show);
    }

    public void saveAll(List<ShowSeat> showSeats){
        showSeatRepository.saveAll(showSeats);
    }
}
