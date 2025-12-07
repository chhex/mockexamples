package com.example.mock.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.mock.db.UserRepository;
import com.example.mock.model.User;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceWIthMockitoTest {

    @Mock
    UserRepository userRepository;

    @Mock
    EmailService emailService;

    @InjectMocks
    UserServiceDefaultImpl userService;

    @Test
    void registersNewUser_andSendsWelcomeMail() {
        // Arrange
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);

        // Act
        userService.register("test@example.com", "Test User");

        // Assert
        // wurde gespeichert?
        verify(userRepository).save(any(User.class));

        // wurde Welcome-Mail verschickt?
        verify(emailService).sendWelcomeMail(any(User.class));
    }

    @Test
    void throwsException_whenEmailAlreadyExists() {
        // Arrange
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                userService.register("test@example.com", "Test User")
        );

        // Save & Mail dürfen NICHT aufgerufen werden
        verify(userRepository, never()).save(any());
        verify(emailService, never()).sendWelcomeMail(any());
    }
}
