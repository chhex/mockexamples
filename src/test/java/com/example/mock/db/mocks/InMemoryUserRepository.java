package com.example.mock.db.mocks;

import com.example.mock.db.UserRepository;
import com.example.mock.model.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {

    private final List<User> savedUsers = new ArrayList<>();
    private final List<String> existingEmails = new ArrayList<>();

    @Override
    public boolean existsByEmail(String email) {
        return existingEmails.contains(email);
    }

    @Override
    public void save(User user) {
        savedUsers.add(user);
        existingEmails.add(user.getEmail());
    }

    @Override
    public List<User> getSavedUsers() {
        return savedUsers;
    }
}
