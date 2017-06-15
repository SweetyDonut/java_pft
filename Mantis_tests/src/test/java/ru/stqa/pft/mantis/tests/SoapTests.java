package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by Даниил on 15.06.2017.
 */
public class SoapTests extends TestBase {
  @Test
  public void testGetProjects() throws  ServiceException, RemoteException {
    Set<Project> projects = null;
    try {
      projects = app.soap().getProject();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    System.out.println(projects.size());
    for (Project project :projects) {
      System.out.println(project.getName());
      
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProject();

    Issue issue=new Issue().withSummary("Test issue")
            .withDescription("Test issue description").withProject(projects.iterator().next());

    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(),created.getSummary() );

  }
}
