package com.ecommerce.user.controller;


//import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/api/users")
    public List<UserResponse> getAllUser(){
        return userService.fetchUsers();
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<Optional<UserResponse>> getUser(@PathVariable String id){
        Optional<UserResponse> u=userService.fetchUserById(id);
        if(u.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(u);
    }

    @PostMapping("/api/users")
    public void createUser(@RequestBody UserRequest userRequest){
        System.out.println("successfully added...");
        userService.addUser(userRequest);
    }

    @PutMapping("/api/users/{id}")
    public void updateUsers(@PathVariable String id,
                           @RequestBody UserRequest userRequest){
        userService.updateUser(id,userRequest);

    }


}
