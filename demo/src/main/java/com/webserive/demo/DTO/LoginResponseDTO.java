package com.webserive.demo.DTO;

public record LoginResponseDTO(String token) {

    public LoginResponseDTO(String token) {
        this.token = token;
    }
}
