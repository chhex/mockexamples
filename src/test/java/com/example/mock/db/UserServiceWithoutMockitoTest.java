package com.example.mock.db;

import org.junit.jupiter.api.Test;
import com.example.mock.db.mocks.InMemoryUserRepository;
import com.example.mock.model.User;
import com.example.mock.service.mocks.FakeEmailService;
import com.example.mock.service.UserService;
import com.example.mock.service.UserServiceDefaultImpl;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceWithoutMockitoTest {

    @Test
    void registersNewUser_andSendsWelcomeMail() {
        // Arrange
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        FakeEmailService emailService = new FakeEmailService();
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
        userRepository.addExistingEmail("test@example.com");

        FakeEmailService emailService = new FakeEmailService();
        UserService userService = new UserServiceDefaultImpl(userRepository, emailService);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                userService.register("test@example.com", "Test User")
        );

        assertEquals(0, userRepository.getSavedUsers().size());
        assertEquals(0, emailService.getMailedUsers().size());
    }
}