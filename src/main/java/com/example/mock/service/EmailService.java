package com.example.mock.service;

import com.example.mock.model.User;
import java.util.List;

/** EmailService interface for sending emails to @User. */
public interface EmailService {
  /**
   * Sends a welcome email to the given @User.
   *
   * @param user the user to send the welcome email to
   */
  void sendWelcomeMail(User user);

  /**
   * Retrieves all users who have been sent welcome emails.
   *
   * @return a list of users who have received welcome emails
   */
  List<User> getMailedUsers();
}
