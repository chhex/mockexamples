package com.example.mock.db.mocks;

import java.util.ArrayList;
import java.util.List;

import com.example.mock.db.UserRepository;
import com.example.mock.model.User;

public class InMemoryUserRepoAlternative implements UserRepository {
    private final List<User> savedUsers = new ArrayList<>();


    @Override
    public boolean existsByEmail(String email) {
        var result = savedUsers.stream().filter(user -> user.getEmail().equals(email)); 
        return result.count() > 0;
    }

    @Override
    public void save(User user) {
        savedUsers.add(user);
    }

   @Override
    public List<User> getSavedUsers() {
        return savedUsers;
    }
}

