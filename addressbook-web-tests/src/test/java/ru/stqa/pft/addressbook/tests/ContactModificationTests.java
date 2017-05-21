package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();

    int before = app.getContactHelper().getGontactCount();

    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("dfgbh", "fghdfh", "fghfghfgh", "fhfghfgh", "fghfghfgh", "yyyyyyyyy", "xxxxxxxx", "raif", "db@mail.ru", null));
    }

    app.getContactHelper().initContactModification(0);
    app.getContactHelper().fillContactForm(new ContactData("dfgbh", "fghdfh", "fghfghfgh", "fhfghfgh", "fghfghfgh", "yyyyyyyyy", "xxxxxxxx", "raif", "db@mail.ru",null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();

    int after = app.getContactHelper().getGontactCount();
    Assert.assertEquals(after, before);
  }

}
