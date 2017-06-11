package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]>validContacts()throws IOException{

    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    File photo = new File("src/test/resources/stru.png");
    List<Object[]> list = new ArrayList<Object[]>();
    String line = reader.readLine();
    while(line!=null){
      String[] split =line.split(";");
      list.add(new Object[]{new ContactData()
              .withFirstname(split[0])
              .withLastname(split[1])
              .withAddress(split[2])
              .withMobilephone(split[3])
              .withMail(split[4])
              .withPhoto(photo)});
      line = reader.readLine();
    }
    return list.iterator();
  }
  @Test (dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {

    app.goTo().HomePage();

    Contacts before = app.contact().all();

    app.contact().create(contact);

    assertThat(app.contact().Count(), equalTo(before.size()+1));
    Set<ContactData> after = app.contact().all();
    assertThat(after, equalTo(before.withAdded( contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }






}
