package com.example.mock.db.mocks;

import com.example.mock.db.UserRepository;
import com.example.mock.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * InMemoryUserRepository An implementation of @UserRepository using in-memory storage in
 * a @ArrayList. Optimized existsByEmail using a HashSet for faster lookups.
 */
public class InMemoryUserRepository implements UserRepository {

  private final List<User> savedUsers = new ArrayList<>();
  private final List<String> existingEmails = new ArrayList<>();

  /**
   * @see UserRepository#existsByEmail(String)
   * 
   * @param email
   * @return boolean
   */
  @Override
  public boolean existsByEmail(String email) {
    return existingEmails.contains(email);
  }

  /**
   * @see UserRepository#save(User)
   * 
   * @param user
   */
  @Override
  public void save(User user) {
    savedUsers.add(user);
    existingEmails.add(user.getEmail());
  }

  /**
   * @see UserRepository#getSavedUsers()
   * @return List<User>
   */
  @Override
  public List<User> getSavedUsers() {
    return savedUsers;
  }
}
