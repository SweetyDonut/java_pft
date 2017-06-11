package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Даниил on 10.06.2017.
 */
public class ContactViewTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();

    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Danil").withLastname("Babin")
              .withHomephone("88-8").withMobilephone("3 33")
              .withWorkphone("777()g").withAddress("polsaya")
              .withMail("1m").withMail2("2m").withMail3("3m"));
    }
  }


  @Test
  public void testViewPage() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromViewPage = app.contact().infoFromViewPage(contact);
    ContactData contactInfoFromEditor = app.contact().infoFromEditForm(contact);
    assertThat(cleaned(contactInfoFromViewPage.getAllInfo()), equalTo(mergeDetails(contactInfoFromEditor)));
  }

  private String  mergeDetails(ContactData contact) {
    return Arrays.asList(contact.getFirstname() + " " + contact.getLastname(), contact.getAddress(),
            "\n" + contact.getHomephone(),contact.getMobilephone(),contact.getWorkPhone(),
            "\n" + contact.getMail(), contact.getMail2(), contact.getMail3())
            .stream().filter((s)->!(s == null || s.equals("")))
            .collect(Collectors.joining("\n"));
  }
  public static String cleaned(String allInfo){
    return allInfo.replaceAll("H: ","").replaceAll("M: ","").replaceAll("W: ","");
  }



}
