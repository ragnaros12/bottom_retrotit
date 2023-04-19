package com.example.project.Models;

public class LoginRequest {
    private final String email, password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
