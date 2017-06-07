package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getNavigationHelper().goToHomePage();

    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("dfgbh", "fghdfh", "fghfghfgh", "fhfghfgh", "fghfghfgh", "yyyyyyyyy", "xxxxxxxx", "raif", "db@mail.ru", null));
    }

  }
  @Test
  public void testContactModification() {


    List<ContactData> before = app.getContactHelper().getGontactList();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "dfgbh", "fghdfh", "fghfghfgh", "fhfghfgh", "fghfghfgh", "yyyyyyyyy", "xxxxxxxx", "raif", "db@mail.ru", null);

    app.getContactHelper().modifyContact(index,contact);

    List<ContactData> after = app.getContactHelper().getGontactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


  }

}
