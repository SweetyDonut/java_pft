package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Даниил on 10.06.2017.
 */
public class ContactViewTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();

    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Danil").withLastname("Babin"));
    }
  }

  @Test
  public void testViewPage() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromViewPage = app.contact().infoFromViewPage(contact);


    ContactData contactInfoFromEditor = app.contact().infoFromEditForm(contact);


    assertThat(contactInfoFromViewPage.getFirstname(), equalTo(contactInfoFromEditor.getFirstname()));
    assertThat(contactInfoFromViewPage.getLastname(), equalTo(contactInfoFromEditor.getLastname()));
    assertThat(contactInfoFromViewPage.getAddress(), equalTo(contactInfoFromEditor.getAddress()));
    assertThat(contactInfoFromViewPage.getHomephone(), equalTo(contactInfoFromEditor.getHomephone()));
    assertThat(contactInfoFromViewPage.getMobilephone(), equalTo(contactInfoFromEditor.getMobilephone()));
    assertThat(contactInfoFromViewPage.getWorkPhone(), equalTo(contactInfoFromEditor.getWorkPhone()));
    assertThat(contactInfoFromViewPage.getMail(), equalTo(contactInfoFromEditor.getMail()));
    assertThat(contactInfoFromViewPage.getMail2(), equalTo(contactInfoFromEditor.getMail2()));
    assertThat(contactInfoFromViewPage.getMail3(), equalTo(contactInfoFromEditor.getMail3()));
  }


}
