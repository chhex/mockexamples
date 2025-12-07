package com.example.mock.service;

/** UserService interface for @User related operations. */
public interface UserService {

  /**
   * Registers a new user with the given email and name.
   *
   * @param email the email of the user
   * @param name the name of the user
   */
  void register(String email, String name);
}
