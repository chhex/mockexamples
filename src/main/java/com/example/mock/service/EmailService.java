package com.example.mock.service;

import com.example.mock.model.User;

import java.util.List;

public interface EmailService {
    void sendWelcomeMail(User user);

    List<User> getMailedUsers();
}
