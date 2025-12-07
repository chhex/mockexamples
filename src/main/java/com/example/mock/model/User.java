package com.example.mock.model;

/** Readonly User model class. */
public class User {
  private final String email;
  private final String name;

  public User(String email, String name) {
    this.email = email;
    this.name = name;
  }

  /**
   * Returns the email of the user.
   *
   * @return String
   */
  public String getEmail() {
    return email;
  }

  /**
   * returns the name of the user.
   *
   * @return String
   */
  public String getName() {
    return name;
  }
}
