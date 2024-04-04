package com.lld.bookmyshow.repositories;

import com.lld.bookmyshow.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
    public Optional<Show> findById(int showId);
}
