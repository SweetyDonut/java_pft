package ru.stqa.pft.mantis.tests;

/**
 * Created by Даниил on 13.06.2017.
 */


import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class PasswordChangeTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }


  @Test
  public void testUserPasswordChange() throws IOException, MessagingException, javax.mail.MessagingException, ServiceException {
    isIssueOpen(00000002);
    HttpSession session = app.newSession();
    User user = app.db().getUser();
    String newPassword = "psswd";
    app.admin().administratorLogin(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));

    app.admin().changeUserPass(user);


    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());

    app.registration().finish(confirmationLink, newPassword);
    Assert.assertTrue(app.newSession().login(user.getUsername(),newPassword));

    assertTrue(app.newSession().login(user.getUsername(), newPassword));
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}


