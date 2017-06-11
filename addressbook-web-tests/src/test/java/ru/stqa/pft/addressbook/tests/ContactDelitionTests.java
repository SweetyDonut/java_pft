package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDelitionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size()==0){
      app.goTo().HomePage();
      app.contact().create(new ContactData().withFirstname("Danil")
              .withLastname("Babin"));}

  }
  @Test
  public void testContactDelition() {

    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();

    app.contact().delite(deletedContact);
    app.goTo().HomePage();

    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
