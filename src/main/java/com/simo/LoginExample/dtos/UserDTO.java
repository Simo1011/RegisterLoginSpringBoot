package com.simo.LoginExample.dtos;

import com.simo.LoginExample.model.User;

public class UserDTO {
    private Long id;
    private String username;

    // Constructor
    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}