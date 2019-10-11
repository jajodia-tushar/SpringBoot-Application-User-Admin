package com.tavisca.springapplication.controller;


import com.tavisca.springapplication.dto.PostClassDataFormat;
import com.tavisca.springapplication.exception.RequestUserNotFoundException;
import com.tavisca.springapplication.model.User;
import com.tavisca.springapplication.repository.UserRepository;
import com.tavisca.springapplication.utility.UserFields;
import com.tavisca.springapplication.utility.UserHelper;
import com.tavisca.springapplication.utility.validate.Operation;
import com.tavisca.springapplication.utility.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private User completeUser;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(@RequestBody String username){
        User operatingUser = this.userRepository.findByUsername(username); // Add if Null condition
        if(UserRole.canDoOperation(operatingUser,Operation.ACCESSMANY))
        {
            logger.info("GetRequest on getAllUsers() Method by  "+ operatingUser.getUid());
            return new ResponseEntity<>(this.userRepository.findAll(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("You cannot Access others Id", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getSingleUser(@PathVariable("id") Integer idRequested,@RequestBody String username) throws RequestUserNotFoundException {
        User operatingUser = this.userRepository.findByUsername(username);

        if(UserRole.canDoOperation(operatingUser,Operation.ACCESSONE,idRequested))
        {
            Optional<User> requestedUser = this.userRepository.findById(idRequested);
            logger.info("-=- GetRequest on getSingleUser() Method for the Id  "+idRequested+"-=-");
            return requestedUser.isPresent() ? new ResponseEntity<>(requestedUser.get(),HttpStatus.OK)
            : new ResponseEntity<>("No User Found With the Id "+idRequested,HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>("You cannot Access others Id",HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody PostClassDataFormat object){
        String username = object.getUsername();
        User operatingUser = this.userRepository.findByUsername(username);

        if(UserRole.canDoOperation(operatingUser, Operation.CREATE)){
            User user = object.getUser();
            completeUser = UserHelper.createUser(user);
            this.userRepository.save(completeUser);
            return new ResponseEntity<>("Created", HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>("Only Admins Can Create New User",HttpStatus.FORBIDDEN);
        }

    }

    @PutMapping("/users/update/{updatingUserUsername}")
    public ResponseEntity<?> updateUser(@PathVariable String updatingUserUsername,@RequestBody PostClassDataFormat object){
        User updatingUserOldValue = this.userRepository.findByUsername(updatingUserUsername);
        User updatingUserNewValue = object.getUser();

        User operatingUser = this.userRepository.findByUsername(object.getUsername());

        if(UserRole.canDoOperation(operatingUser,Operation.UPDATE)){
            List<UserFields> numberOfChangedField = updatingUserOldValue.getNumberOfChangedField(updatingUserNewValue);
            if (UserRole.canUpdate(operatingUser, numberOfChangedField)) {
                User updateUser = UserHelper.copyUserDetails(updatingUserOldValue, updatingUserNewValue, numberOfChangedField);
                this.userRepository.save(updateUser);
                return new ResponseEntity<>("Done", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("You cannot Change All Specified Properties  ", HttpStatus.FORBIDDEN);
            }
        }
        else{
            return new ResponseEntity<>("You cannot Update the User", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id,@RequestBody String username){

        User operatingUser = this.userRepository.findByUsername(username);
        if(UserRole.canDoOperation(operatingUser,Operation.DELETE)) {
            Optional<User> userBeingDeleted = this.userRepository.findById(id);
            if (userBeingDeleted.isPresent()) {
                this.userRepository.deleteById(id);
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            } else
                return new ResponseEntity<>("No User Found with Id " + id, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>("You are not Allowed to Do Delete Operation", HttpStatus.NOT_FOUND);
    }
}
