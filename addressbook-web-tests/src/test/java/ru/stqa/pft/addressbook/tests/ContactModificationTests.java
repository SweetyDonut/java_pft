package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("dfgbh", "fghdfh", "fghfghfgh", "fhfghfgh", "fghfghfgh", "yyyyyyyyy", "xxxxxxxx", "raif", "db@mail.ru"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }

}
