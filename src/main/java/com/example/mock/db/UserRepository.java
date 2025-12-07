package com.example.mock.db;
import java.util.List;

import com.example.mock.model.User;
public interface UserRepository {
    boolean existsByEmail(String email);
    void save(User user);
    List<User> getSavedUsers();
}
