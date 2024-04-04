package com.lld.bookmyshow.services;

import com.lld.bookmyshow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserService {
   Optional<User> findById(int userId);
}
