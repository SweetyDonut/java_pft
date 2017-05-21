package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToHomePage();
    int before = app.getContactHelper().getGontactCount();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("babin", "Vadimovich", "Daniil", "SweetyDonut", "SumProezd", "yyyyyyyyy", "xxxxxxxx", "raif", "db@mail.ru", null),true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
    int after = app.getContactHelper().getGontactCount();
    Assert.assertEquals(after,before+1);
  }


}
