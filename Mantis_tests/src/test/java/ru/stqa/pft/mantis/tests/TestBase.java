package ru.stqa.pft.mantis.tests;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;


/**
 * Created by Даниил on 21.05.2017.
 */
public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)

  public void setUp() throws Exception {

    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"),"config/config_inc.php","config/config_inc.php.bak");

  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {

    app.ftp().restore("config_inc.php.bak","config_inc.php");
    app.stop();
  }
  boolean isIssueOpen(int issueId) throws MalformedURLException, RemoteException, ServiceException {
    MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("web.soapURL")));
    return !(mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId)).getStatus().getName()=="resolved");
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }




}