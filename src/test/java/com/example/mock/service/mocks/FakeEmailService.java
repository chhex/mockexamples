package com.example.mock.service.mocks;

import com.example.mock.model.User;
import com.example.mock.service.EmailService;
import java.util.ArrayList;
import java.util.List;

public class FakeEmailService implements EmailService {

  private final List<User> mailedUsers = new ArrayList<>();

  /**
   * @param user
   */
  @Override
  public void sendWelcomeMail(User user) {
    mailedUsers.add(user);
  }

  /**
   * @return List<User>
   */
  public List<User> getMailedUsers() {
    return mailedUsers;
  }
}
