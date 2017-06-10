package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.goTo().HomePage();

    Set<ContactData> before = app.Contact().all();
    ContactData contact = new ContactData().withFirstname("Danil").withLastname("Babin");

    app.Contact().create(contact);

    Set<ContactData> after = app.Contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);


    contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}
