package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Даниил on 10.06.2017.
 */


public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();

    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Danil").withLastname("Babin"));
    }
  }

    @Test
    public void testContactPhone () {
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditor = app.contact().infoFromEditForm(contact);
      MatcherAssert.assertThat(contact.getAllPhones(), CoreMatchers.equalTo(getMergePhones(contactInfoFromEditor)));

    }

  private String getMergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomephone(),contact.getMobilephone(),contact.getWorkPhone())
            .stream().filter((s)->!s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }

  }

