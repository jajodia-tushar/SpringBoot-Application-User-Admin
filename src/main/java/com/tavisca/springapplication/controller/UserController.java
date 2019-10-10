package com.tavisca.springapplication.controller;


import com.tavisca.springapplication.exception.RequestUserNotFoundException;
import com.tavisca.springapplication.model.User;
import com.tavisca.springapplication.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @GetMapping("/users")
    public List<User> getAllUsers(){
        logger.info("-=- GetRequest on getAllUsers() Method -=-");
        return this.userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getSingleUser(@PathVariable("id") Integer id) throws RequestUserNotFoundException {
        Optional<User> requestedUser = this.userRepository.findById(id);
        logger.info("-=- GetRequest on getSingleUser() Method for the Id  "+id+"-=-");
        return requestedUser.orElseThrow(()->new RequestUserNotFoundException("The User with the Id "+id+" is not Present"));

    }




}
