package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactDelitionTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions(){
    app.getNavigationHelper().goToHomePage();

    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("dfgbh", "fghdfh", "fghfghfgh", "fhfghfgh", "fghfghfgh", "yyyyyyyyy", "xxxxxxxx", "raif", "db@mail.ru", null));
    }
  }
  @Test
  public void testContactDelition() {

    List<ContactData> before = app.getContactHelper().getGontactList();

    int index = before.size() - 1;

    app.getContactHelper().deliteContact(index);
    app.getNavigationHelper().goToHomePage();

    List<ContactData> after = app.getContactHelper().getGontactList();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);


  }

}
