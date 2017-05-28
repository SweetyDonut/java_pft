package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper().goToHomePage();

    List<ContactData> before = app.getContactHelper().getGontactList();

    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("babin", "Vadimovich", "Daniil", "SweetyDonut", "SumProezd", "yyyyyyyyy", "xxxxxxxx", "raif", "db@mail.ru", null), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();

    List<ContactData> after = app.getContactHelper().getGontactList();
    Assert.assertEquals(after.size(), before.size() + 1);


  }


}
