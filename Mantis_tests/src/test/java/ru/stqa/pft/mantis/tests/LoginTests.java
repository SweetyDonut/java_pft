package ru.stqa.pft.mantis.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

import static org.testng.Assert.*;

/**
 * Created by Даниил on 12.06.2017.
 */
public class LoginTests extends TestBase {




  @Test()

  public void testLogin() throws IOException, ServiceException {

    HttpSession session = app.newSession();

    assertTrue(session.login("user9","password"));
    assertTrue(session.isLoggedInAs("user9"));
  }
}
