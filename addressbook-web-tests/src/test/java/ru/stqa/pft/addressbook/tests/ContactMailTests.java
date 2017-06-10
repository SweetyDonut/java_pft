package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Даниил on 10.06.2017.
 */


public class ContactMailTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();

    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Danil").withLastname("Babin"));
    }
  }

  @Test
  public void testContactMail(){
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditor = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllMails(), equalTo(getMergePhones(contactInfoFromEditor)));

  }

  private String getMergePhones(ContactData contact) {
    return Arrays.asList(contact.getMail(),contact.getMail2(),contact.getMail3())
            .stream().filter((s -> !s.equals("")))
            .collect(Collectors.joining("\n"));
  }
}
