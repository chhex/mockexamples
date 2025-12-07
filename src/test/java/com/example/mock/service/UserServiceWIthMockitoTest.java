package com.example.mock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.mock.db.UserRepository;
import com.example.mock.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

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
        when(userRepository.getSavedUsers()).thenReturn(Arrays.asList(new User("test@example.com", " Test User")));
        when(emailService.getMailedUsers()).thenReturn(Arrays.asList(new User("test@example.com", " Test User")));
        // Act
        userService.register("test@example.com", "Test User");


        // Assert
        assertEquals(1, userRepository.getSavedUsers().size());
        User saved = userRepository.getSavedUsers().get(0);
        assertEquals("test@example.com", saved.getEmail());

        assertEquals(1, emailService.getMailedUsers().size());
        User mailed = emailService.getMailedUsers().get(0);
        assertEquals("test@example.com", mailed.getEmail());
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
