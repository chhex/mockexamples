package com.example.mock.db;
import com.example.mock.model.User;
public interface UserRepository {
    boolean existsByEmail(String email);
    void save(User user);
}