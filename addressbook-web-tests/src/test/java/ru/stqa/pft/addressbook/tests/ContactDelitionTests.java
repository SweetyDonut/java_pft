package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactDelitionTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();

    if (app.Contact().all().size()==0) {
      app.Contact().create(new ContactData().withFirstname("Danil").withLastname("Babin"));
    }
  }
  @Test
  public void testContactDelition() {

    Set<ContactData> before = app.Contact().all();
    ContactData deletedContact = before.iterator().next();



    app.Contact().delite(deletedContact);
    app.goTo().HomePage();

    Set<ContactData> after = app.Contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);


  }

}
