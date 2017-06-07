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
    app.goTo().HomePage();

    if (app.Contact().List().size()==0) {
      app.Contact().create(new ContactData("dfgbh", "fghdfh", "fghfghfgh", "fhfghfgh", "fghfghfgh", "yyyyyyyyy", "xxxxxxxx", "raif", "db@mail.ru", null));
    }
  }
  @Test
  public void testContactDelition() {

    List<ContactData> before = app.Contact().List();

    int index = before.size() - 1;

    app.Contact().delite(index);
    app.goTo().HomePage();

    List<ContactData> after = app.Contact().List();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);


  }

}
