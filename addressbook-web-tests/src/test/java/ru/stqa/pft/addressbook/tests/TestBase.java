package ru.stqa.pft.addressbook.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import com.google.gson.*;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.acl.Group;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


/**
 * Created by Даниил on 21.05.2017.
 */
public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)

  public void setUp() throws Exception {
    app.init();

  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + " with parametrs " + Arrays.asList(p));

  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test " + m.getName());
  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUi")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUI() {
    if (Boolean.getBoolean("verifyUi")) {
      Contacts dbGroups = app.db().contacts();
      Contacts uiGroups = app.contact().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new ContactData()
                      .withId(g.getId())
                      .withFirstname(g.getFirstname())
                      .withLastname(g.getLastname())
                      .withAllMails(getMergeMails(g))
                      .withAllPhones(getMergePhones(g))
                      .withAddress(g.getAddress())).collect(Collectors.toSet())));
    }
  }

  private String getMergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  private String getMergeMails(ContactData contact) {
    return Arrays.asList(contact.getMail(), contact.getMail2(), contact.getMail3())
            .stream().filter((s -> !s.equals("")))
            .collect(Collectors.joining("\n"));
  }

  boolean isIssueOpenInMantis(int issueId) throws MalformedURLException, RemoteException, ServiceException {
    MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("web.soapURL")));
    return !(mc.mc_issue_get("administrator", "root",
            BigInteger.valueOf(issueId)).getStatus().getName().equals("resolved"));
  }

  public void skipIfNotFixedInMantis(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpenInMantis(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }


  boolean isIssueOpenInBugify(int issueId) throws IOException {

    String json = Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "")
            .execute(Request.Get((String.format("http://demo.bugify.com/api/issues/%s.json", issueId)))).returnContent().asString();

    JsonObject parsed = new JsonParser().parse(json).getAsJsonObject();

    JsonArray issues = parsed.getAsJsonArray("issues");

    JsonObject info = issues.get(0).getAsJsonObject();

    return !(info.get("state_name").getAsString().equals("Closed")) ;
  }

  public void skipIfNotFixedInBugify(int issueId) throws IOException {
    if (isIssueOpenInBugify(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}




