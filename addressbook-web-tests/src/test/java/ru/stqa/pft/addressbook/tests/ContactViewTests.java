package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Даниил on 10.06.2017.
 */
public class ContactViewTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();

    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Danil").withLastname("Babin")
              .withHomephone("88-8").withMobilephone("3 33")
              .withWorkphone("777()g").withAddress("polsaya")
              .withMail("1m").withMail2("2m").withMail3("3m"));
    }
    app.goTo().HomePage();
  }


  @Test
  public void testViewPage() {
    ContactData contact = app.db().contacts().iterator().next();
    ContactData contactInfoFromViewPage = app.contact().infoFromViewPage(contact);
    ContactData contactInfoFromEditor = app.contact().infoFromEditForm(contact);
    assertThat(contactInfoFromViewPage.getAllInfo(), equalTo(mergeDetails(contactInfoFromEditor)));
  }

  private String mergeDetails(ContactData contact) {


    return Arrays.asList(Arrays.asList(contact.getFirstname() + " " , contact.getLastname())
                    .stream().filter((s) -> !(s.equals(" "))).collect(Collectors.joining()),contact.getAddress(),
            Arrays.asList("","H: " + contact.getHomephone(),"M: " + contact.getMobilephone(), "W: " + contact.getWorkPhone())
            .stream().filter((s) -> !(s.equals("M: ") || s.equals("W: ") || s.equals("H: "))).collect(Collectors.joining("\n")),
            "\n"+Arrays.asList(contact.getMail(), contact.getMail2(), contact.getMail3()).stream()
                    .filter((s) -> !(s == null || s.equals(""))).collect(Collectors.joining("\n")))
            .stream().filter((s) -> !(s == null || s.equals("")))
           .collect(Collectors.joining("\n"));
  }


