package com.webserive.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webserive.demo.DTO.AuthenticationDTO;
import com.webserive.demo.DTO.LoginResponseDTO;
import com.webserive.demo.DTO.RegisterDTO;
import com.webserive.demo.entities.RoleUser;
import com.webserive.demo.entities.User;
import com.webserive.demo.repository.UserRepository;
import com.webserive.demo.services.TokenService;

import lombok.var;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO data) {
        if (this.userRepository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User novoUsuario = new User(data.name(), data.email(), encryptedPassword, data.phone());
        novoUsuario.setRole(RoleUser.USER);

        this.userRepository.save(novoUsuario);

        return ResponseEntity.ok(Map.of("success", true, "message", "Usuário cadastrado com sucesso"));

    }

}