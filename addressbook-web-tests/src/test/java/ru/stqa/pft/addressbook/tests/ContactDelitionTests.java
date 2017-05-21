package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactDelitionTests extends TestBase {

  @Test
  public void testContactDelition(){
      app.getNavigationHelper().goToHomePage();

      app.getContactHelper().selectContact();
      app.getContactHelper().initContactDelition();
      app.getContactHelper().submitContactDelition();
      app.getNavigationHelper().goToHomePage();
    }

}
