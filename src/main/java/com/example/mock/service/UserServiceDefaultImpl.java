package com.example.mock.service;

import com.example.mock.db.UserRepository;
import com.example.mock.model.User;

public class UserServiceDefaultImpl implements UserService {

  private final UserRepository userRepository;
  private final EmailService emailService;

  public UserServiceDefaultImpl(UserRepository userRepository, EmailService emailService) {
    this.userRepository = userRepository;
    this.emailService = emailService;
  }

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
