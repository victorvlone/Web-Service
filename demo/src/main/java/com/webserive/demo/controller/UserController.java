package com.webserive.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webserive.demo.entities.User;
import com.webserive.demo.services.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService; 

    @PostMapping("/new")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try {
            User newUser = userService.createUser(user);
            return ResponseEntity.ok().body("Usuario criado com sucesso! ID: " + newUser.getId());
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Erro ao criar usuario: " + e.getMessage());
        }
    }

}
