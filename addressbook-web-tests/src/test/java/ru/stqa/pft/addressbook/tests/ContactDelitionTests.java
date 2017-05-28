package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactDelitionTests extends TestBase {

  @Test
  public void testContactDelition() {

    app.getNavigationHelper().goToHomePage();

    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("dfgbh", "fghdfh", "fghfghfgh", "fhfghfgh", "fghfghfgh", "yyyyyyyyy", "xxxxxxxx", "raif", "db@mail.ru", null));
    }
    List<ContactData> before = app.getContactHelper().getGontactList();

    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactDelition();
    app.getContactHelper().submitContactDelition();
    app.getNavigationHelper().goToHomePage();

    List<ContactData> after = app.getContactHelper().getGontactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);


  }

}
