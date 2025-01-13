package com.SDP.project.controllers;

import com.SDP.project.models.Account;
import com.SDP.project.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Account>> allUsers() {
        List <Account> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}