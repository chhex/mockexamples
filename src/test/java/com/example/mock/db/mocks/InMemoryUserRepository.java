package com.example.mock.db.mocks;

import com.example.mock.db.UserRepository;
import com.example.mock.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>InMemoryUserRepository</b> is an implementation of @UserRepository using in-memory storage in
 * a @ArrayList. Optimized existsByEmail using a HashSet for faster lookups.
 */
public class InMemoryUserRepository implements UserRepository {

  private final List<User> savedUsers = new ArrayList<>();
  private final List<String> existingEmails = new ArrayList<>();

  /**
   * <b>existsByEmail</b>.
   *
   * @see UserRepository#existsByEmail(String)
   * @param email Email to check
   * @return boolean 
   */
  @Override
  public boolean existsByEmail(String email) {
    return existingEmails.contains(email);
  }

  /**
   * <b>save</b>.
   *
   * @see UserRepository#save(User)
   * @param user User to save
   */
  @Override
  public void save(User user) {
    savedUsers.add(user);
    existingEmails.add(user.getEmail());
  }

  /**
   * <b>getSavedUsers</b>.
   *
   * @see UserRepository#getSavedUsers()
   * @return @List of @User
   */
  @Override
  public List<User> getSavedUsers() {
    return savedUsers;
  }
}
