package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();

    if (app.Contact().List().size()==0) {
      app.Contact().create(new ContactData().withFirstname("Danil").withLastname("Babin"));
    }

  }
  @Test
  public void testContactModification() {


    List<ContactData> before = app.Contact().List();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withFirstname("Danil").withLastname("Babin").withId(before.get(index).getId());

    app.Contact().modify(index,contact);

    List<ContactData> after = app.Contact().List();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);



  }

}
