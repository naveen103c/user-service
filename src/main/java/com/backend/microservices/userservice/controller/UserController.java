package com.backend.microservices.userservice.controller;

import com.backend.microservices.userservice.dto.User;
import com.backend.microservices.userservice.exception.NoUserPresentException;
import com.backend.microservices.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class UserController {

//    private static final Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> UserList = userService.getAllUserService();
        return ResponseEntity.status(HttpStatus.OK).body(UserList);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) throws NoUserPresentException {
        User User = userService.getUserByIdService(userId);
        return ResponseEntity.status(HttpStatus.OK).body(User);
    }

    @PostMapping("/users/add")
    public ResponseEntity<User> addUser(@RequestBody @Valid User User){
        User= userService.addUserService(User);
        return ResponseEntity.status(HttpStatus.CREATED).body(User);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUserById(@RequestBody User User){
        User = userService.updateUserByIdService(User);
        return ResponseEntity.status(HttpStatus.OK).body(User);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable("id") String userId){
        boolean isDeleted = userService.deleteUserByIdService(userId);
        return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
    }
}
