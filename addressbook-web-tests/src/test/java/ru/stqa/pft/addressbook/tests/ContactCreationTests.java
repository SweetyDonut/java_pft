package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("babin", "Vadimovich", "Daniil", "SweetyDonut", "SumProezd", "yyyyyyyyy", "xxxxxxxx", "raif", "db@mail.ru"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }


}
