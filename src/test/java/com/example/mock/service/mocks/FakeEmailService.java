package com.example.mock.service.mocks;

import com.example.mock.model.User;
import com.example.mock.service.EmailService;
import java.util.ArrayList;
import java.util.List;
/** 
 * FakeEmailService is a mock implementation of @EmailService for testing purposes.
 */

public class FakeEmailService implements EmailService {

  private final List<User> mailedUsers = new ArrayList<>();

  /**
   * <b>sendWelcomeMail</b>.
   *
   * @see EmailService#sendWelcomeMail(User)
   * @param user @User to send mail to
   */
  @Override
  public void sendWelcomeMail(User user) {
    mailedUsers.add(user);
  }

  /**
   * <b>getMailedUsers</b>.
   *
   * @see EmailService#getMailedUsers()
   * @return List of all mailed @User
   */
  public List<User> getMailedUsers() {
    return mailedUsers;
  }
}
