package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import javax.xml.rpc.ServiceException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size()==0){
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().HomePage();
  }
  @DataProvider
  public Iterator<Object[]>validContactsFromXml()throws IOException {

    try (BufferedReader reader = new BufferedReader(
            new FileReader(
                    new File("src/test/resources/contactss.xml")))) {
     /* File photo = new File("src/test/resources/stru.png");*/
      String xml = " ";
      List<Object[]> list = new ArrayList<Object[]>();
      String line = reader.readLine();
      while (line != null) {
        xml += line;
      /*String[] split =line.split(";");
      list.add(new Object[]{new ContactData()
              .withFirstname(split[0])
              .withLastname(split[1])
              .withAddress(split[2])
              .withMobilephone(split[3])
              .withMail(split[4])
              .withPhoto(photo)})*/
        ;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);

   /*   for (ContactData contact: contacts
           ) {
        contact.withPhoto(photo);
      }*/

      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]>validContactsFromJson()throws IOException {
    try (BufferedReader reader = new BufferedReader(
            new FileReader(
                    new File("src/test/resources/contacts.json")))) {
      //File photo = new File("src/test/resources/stru.png");
      String json = " ";
      List<Object[]> list = new ArrayList<Object[]>();
      String line = reader.readLine();
      while (line != null) {
        json += line;

        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());

 /*     for (ContactData contact: contacts
              ) {
        contact.withPhoto(photo);
      }*/
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }




  @Test (dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) throws IOException, ServiceException {
    skipIfNotFixedInBugify(5);
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    contact.inGroup(groups.iterator().next());

    app.contact().create(contact);
    app.goTo().HomePage();
    System.out.println(app.contact().Count());
    assertThat(app.contact().Count(), equalTo(before.size()+1));
    Set<ContactData> after = app.db().contacts();
    assertThat(after, equalTo(before.withAdded( contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }






}
