package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactDelitionTests extends TestBase {

  @Test
  public void testContactDelition() {

    app.getNavigationHelper().goToHomePage();
    int before = app.getContactHelper().getGontactCount();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("dfgbh", "fghdfh", "fghfghfgh", "fhfghfgh", "fghfghfgh", "yyyyyyyyy", "xxxxxxxx", "raif", "db@mail.ru", null));
    }
    app.getContactHelper().selectContact(before-1);
    app.getContactHelper().initContactDelition();
    app.getContactHelper().submitContactDelition();
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getGontactCount();
    Assert.assertEquals(after, before-1);
  }

}
