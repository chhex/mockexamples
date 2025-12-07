package com.example.mock.db;

import com.example.mock.model.User;
import java.util.List;

/** UserRepository interface for storing @User. */
public interface UserRepository {
  /**
   * Checks if a @User with the given email exists in the repository.
   *
   * @param email the email to check
   * @return true if a user with the email exists, false otherwise
   */
  boolean existsByEmail(String email);

  /**
   * Saves a @User to the repository.
   *
   * @param user the user to save
   */
  void save(User user);

  /**
   * Retrieves all saved users from the repository.
   *
   * @return a list of saved users
   */
  List<User> getSavedUsers();
}
