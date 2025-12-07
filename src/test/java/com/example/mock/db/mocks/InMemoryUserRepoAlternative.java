package com.example.mock.db.mocks;

import com.example.mock.db.UserRepository;
import com.example.mock.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * InMemoryUserRepoAlternative An alternative implementation of @UserRepository using in-memory
 * storage in a @List. And example of using @Collection Stream API to check for existing emails.
 */
public class InMemoryUserRepoAlternative implements UserRepository {
  private final List<User> savedUsers = new ArrayList<>();

  /**
   * @see UserRepository#existsByEmail(String)
   * checks using @Collection Stream API
   * @param email
   * @return boolean
   */
  @Override
  public boolean existsByEmail(String email) {
    var result = savedUsers.stream().filter(user -> user.getEmail().equals(email));
    return result.count() > 0;
  }

  /**
   * @see UserRepository#save(User)
   *
   * @param user
   */
  @Override
  public void save(User user) {
    savedUsers.add(user);
  }

  /**
   * @see UserRepository#getSavedUsers()
   *
   * @return List<User>
   */
  @Override
  public List<User> getSavedUsers() {
    return savedUsers;
  }
}
