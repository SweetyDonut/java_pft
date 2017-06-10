package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Даниил on 10.06.2017.
 */
public class ContactAdressTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();

    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Danil").withLastname("Babin"));
    }
  }

  @Test
  public void testContactAdress(){
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditor = app.contact().infoFromEditForm(contact);
    MatcherAssert.assertThat(contact.getAddress(), CoreMatchers.equalTo(contactInfoFromEditor.getAddress()));

  }
}
