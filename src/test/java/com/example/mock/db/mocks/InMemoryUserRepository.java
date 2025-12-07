package com.example.mock.db.mocks;
import java.util.ArrayList;
import java.util.List;

import com.example.mock.db.UserRepository;
import com.example.mock.model.User;

public class InMemoryUserRepository implements UserRepository {

    private final List<User> savedUsers = new ArrayList<>();
    private final List<String> existingEmails = new ArrayList<>();

    public void addExistingEmail(String email) {
        existingEmails.add(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return existingEmails.contains(email);
    }

    @Override
    public void save(User user) {
        savedUsers.add(user);
    }

    public List<User> getSavedUsers() {
        return savedUsers;
    }
}