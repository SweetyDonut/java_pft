package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;


/**
 * Created by Даниил on 21.05.2017.
 */
public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)

  public void setUp() throws Exception {

    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.bak");

  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {

    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
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







