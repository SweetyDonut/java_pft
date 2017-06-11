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

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size()==0){
      app.goTo().HomePage();
      app.contact().create(new ContactData().withFirstname("Danil")
              .withLastname("Babin"));
    }

  }
  @Test
  public void testContactModification() {


    Contacts before = app.db().contacts();

    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withFirstname("Dks").withLastname("Basfdasin").withId(modifiedContact.getId());

    app.contact().modify(contact);
    app.goTo().HomePage();

    assertThat(app.contact().Count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withModified(modifiedContact,contact)));
  }

}
