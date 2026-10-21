package com.bookmyshow.demo.service;

import com.bookmyshow.demo.models.User;
import com.bookmyshow.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IService {

    @Autowired
    private UserRepo userRepo;


    public User saveUser(User user) {
        userRepo.save(user);
        return user;
    }

    public User getUserById(String userId) {
        return userRepo.findById(Integer.valueOf(userId)).orElse(null);
    }

    @Override
    public String getUserName(String username) {
        return "Hello, " + username + "!";
    }
}
