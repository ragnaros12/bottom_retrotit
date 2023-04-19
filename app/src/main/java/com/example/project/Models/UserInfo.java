package com.example.project.Models;

import java.util.List;

public class UserInfo {
    private final String email, telegram;
    private final List<Role> roles;

    public UserInfo(String email, String telegram, List<Role> roles) {
        this.email = email;
        this.telegram = telegram;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public String getTelegram() {
        return telegram;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
