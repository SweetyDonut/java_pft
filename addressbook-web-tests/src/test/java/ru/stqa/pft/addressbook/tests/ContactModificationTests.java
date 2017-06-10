package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();

    if (app.Contact().all().size()==0) {
      app.Contact().create(new ContactData().withFirstname("Danil").withLastname("Babin"));
    }

  }
  @Test
  public void testContactModification() {


    Set<ContactData> before = app.Contact().all();
    ContactData modifiedContact = before.iterator().next();

   ContactData contact = new ContactData().withFirstname("Danil").withLastname("Babin").withId(modifiedContact.getId());

    app.Contact().modify(contact);

    Set<ContactData> after = app.Contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);



  }

}
