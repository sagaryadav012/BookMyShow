package com.lld.bookmyshow.services;

import com.lld.bookmyshow.model.User;
import com.lld.bookmyshow.repositories.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    public Optional<User> findById(int userId){
        return userRepository.findById(userId);
    }
}
