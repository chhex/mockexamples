package com.example.mock.service;

import com.example.mock.db.UserRepository;
import com.example.mock.model.User;

/** Default implementation of @UserService. */
public class UserServiceDefaultImpl implements UserService {

  private final UserRepository userRepository;
  private final EmailService emailService;

  public UserServiceDefaultImpl(UserRepository userRepository, EmailService emailService) {
    this.userRepository = userRepository;
    this.emailService = emailService;
  }

  /**
   * Standard user registration process Saves the @User and sends a welcome email.
   *
   * @param email
   * @param name
   */
  @Override
  public void register(String email, String name) {
    if (userRepository.existsByEmail(email)) {
      throw new IllegalArgumentException("User already exists");
    }

    User user = new User(email, name);
    userRepository.save(user);
    emailService.sendWelcomeMail(user);
  }
}
