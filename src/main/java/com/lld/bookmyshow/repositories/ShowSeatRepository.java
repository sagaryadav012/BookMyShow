package com.lld.bookmyshow.repositories;

import com.lld.bookmyshow.model.Show;
import com.lld.bookmyshow.model.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    Optional<ShowSeat> findById(int showSeatId);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    List<ShowSeat> findShowSeatByIdInAndSeatStatus_AvailableAndShow(List<Integer> showSeatIds, Show show);
}
