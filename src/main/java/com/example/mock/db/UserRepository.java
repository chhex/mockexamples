package com.example.mock.db;

import com.example.mock.model.User;

import java.util.List;

public interface UserRepository {
    boolean existsByEmail(String email);

    void save(User user);

    List<User> getSavedUsers();
}
