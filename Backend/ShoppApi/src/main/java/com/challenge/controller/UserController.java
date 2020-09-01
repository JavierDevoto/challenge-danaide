package com.challenge.controller;

import com.challenge.model.User;
import com.challenge.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/get/{userName}")
    public ResponseEntity<User> getUserById(@PathVariable String username){
        return ResponseEntity.ok().body(userService.getUserByPk(username));
    }

    @PostMapping("/create")
    public ResponseEntity<User> createItem(@RequestBody User user){
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @GetMapping("/get-vip-users")
    public ResponseEntity<List<User>> getClientesVip() {
        return ResponseEntity.ok().body(userService.getVipClients());
    }

}
