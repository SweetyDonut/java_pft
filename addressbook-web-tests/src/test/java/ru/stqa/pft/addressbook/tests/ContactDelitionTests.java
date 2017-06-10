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
public class ContactDelitionTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();

    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData().withFirstname("Danil").withLastname("Babin"));
    }
  }
  @Test
  public void testContactDelition() {

    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();

    app.contact().delite(deletedContact);
    app.goTo().HomePage();

    assertThat(app.contact().Count(), equalTo(before.size()-1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
