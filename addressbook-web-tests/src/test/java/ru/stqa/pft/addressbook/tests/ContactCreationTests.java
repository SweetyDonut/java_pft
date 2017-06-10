package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.goTo().HomePage();

    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Danil").withLastname("Babin");

    app.contact().create(contact);

    assertThat(app.contact().Count(), equalTo(before.size()+1));
    Set<ContactData> after = app.contact().all();
    assertThat(after, equalTo(before.withAdded( contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }




}
