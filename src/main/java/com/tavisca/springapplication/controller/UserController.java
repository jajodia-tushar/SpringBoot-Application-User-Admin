package com.tavisca.springapplication.controller;


import com.tavisca.springapplication.exception.RequestUserNotFoundException;
import com.tavisca.springapplication.utility.UserHelper;
import com.tavisca.springapplication.model.User;
import com.tavisca.springapplication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User user){
        User completeUser = UserHelper.createUser(user);
        this.userRepository.save(completeUser);
        return new ResponseEntity<>("Created", HttpStatus.ACCEPTED);
    }

    @PutMapping("/users/edit/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User newUser){

        User oldUser = this.userRepository.findByUsername(username);
        User updateUser = UserHelper.copyUserDetails(oldUser,newUser);
        this.userRepository.save(newUser);
        return new ResponseEntity<>("Done",HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
        Optional<User> byId = this.userRepository.findById(id);
        if(byId.isPresent()){
            this.userRepository.deleteById(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No User Found with Id "+id, HttpStatus.NOT_FOUND);
        }
    }





}
