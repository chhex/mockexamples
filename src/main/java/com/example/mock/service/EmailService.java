package com.example.mock.service;
import java.util.List;

import com.example.mock.model.User;
public interface EmailService {
    void sendWelcomeMail(User user);
    List<User> getMailedUsers();
}
