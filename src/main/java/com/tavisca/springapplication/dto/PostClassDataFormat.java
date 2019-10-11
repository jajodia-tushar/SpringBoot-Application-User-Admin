package com.tavisca.springapplication.dto;

import com.tavisca.springapplication.model.User;

public class PostClassDataFormat {

    String username;
    User user;

    public PostClassDataFormat() {
    }

    public PostClassDataFormat(String username, User user) {
        this.username = username;
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
