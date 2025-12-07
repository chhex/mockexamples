package com.example.mock.service.mocks;

import java.util.ArrayList;
import java.util.List;
import com.example.mock.model.User;
import com.example.mock.service.EmailService;


public class FakeEmailService implements EmailService {

    private final List<User> mailedUsers = new ArrayList<>();

    @Override
    public void sendWelcomeMail(User user) {
        mailedUsers.add(user);
    }

    public List<User> getMailedUsers() {
        return mailedUsers;
    }
}