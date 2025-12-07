package com.example.mock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.mock.db.UserRepository;
import com.example.mock.db.mocks.InMemoryUserRepoAlternative;
import com.example.mock.db.mocks.InMemoryUserRepository;
import com.example.mock.model.User;
import com.example.mock.service.mocks.FakeEmailService;
import org.junit.jupiter.api.Test;

public class UserServiceWithoutMockitoTest {

  @Test
  void registersNewUser_andSendsWelcomeMail() {
    // Arrange
    UserRepository userRepository = new InMemoryUserRepoAlternative();
    EmailService emailService = new FakeEmailService();
    UserService userService = new UserServiceDefaultImpl(userRepository, emailService);

    // Act
    userService.register("test@example.com", "Test User");

    // Assert
    assertEquals(1, userRepository.getSavedUsers().size());
    User saved = userRepository.getSavedUsers().get(0);
    assertEquals("test@example.com", saved.getEmail());

    assertEquals(1, emailService.getMailedUsers().size());
    User mailed = emailService.getMailedUsers().get(0);
    assertEquals("test@example.com", mailed.getEmail());
  }

  @Test
  void throwsException_whenEmailAlreadyExists() {
    // Arrange
    InMemoryUserRepository userRepository = new InMemoryUserRepository();
    FakeEmailService emailService = new FakeEmailService();
    UserService userService = new UserServiceDefaultImpl(userRepository, emailService);
    userService.register("test@example.com", "Some User");

    // Act + Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> userService.register("test@example.com", "Another Test User"));

    assertEquals(1, userRepository.getSavedUsers().size());
    assertEquals(1, emailService.getMailedUsers().size());
  }
}
