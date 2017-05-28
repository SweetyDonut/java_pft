package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper().goToHomePage();

    List<ContactData> before = app.getContactHelper().getGontactList();
    ContactData contact = new ContactData("babin", "Vadimovich", "Daniil", "SweetyDonut", "SumProezd", "yyyyyyyyy", "xxxxxxxx", "raif", "db@mail.ru", null);

    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(contact, true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();

    List<ContactData> after = app.getContactHelper().getGontactList();
    Assert.assertEquals(after.size(), before.size() + 1);


    int max=0;
    for (ContactData c: after
            ) {
      if (c.getId() > max){
        max = c.getId();
      }

    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


  }


}
