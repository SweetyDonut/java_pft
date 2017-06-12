package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


/**
 * Created by Даниил on 12.06.2017.
 */

  public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

      if (app.db().groups().size() == 0) {
        app.goTo().GroupPage();
        app.group().create(new GroupData().withName("GroupTest"));
      } else {
        Contacts contactsList = app.db().contacts();
        Groups groupsList = app.db().groups();
        boolean groupFound = false;
        for (ContactData contact : contactsList) {
          for (GroupData group : groupsList) {
            if (!StringUtils.containsIgnoreCase(contact.getGroups().toString(), group.toString())) {
              groupFound = true;
              break;
            }
          }
        }
        if (!groupFound) {
          app.goTo().GroupPage();
          app.group().create(new GroupData().withName("NewGroup"));
        }
      }

      app.goTo().HomePage();
      if (app.db().contacts().size() == 0) {
        app.goTo().HomePage();
        app.contact().create(new ContactData().withFirstname("mrdgdrgdg").withLastname("dfgdfg").withMobilephone("5467567567856").withMail("jvgcv"));
      }
    }


    @Test
    public void testIfContactCouldBeAddedToGroup() {
      Contacts contactsList = app.db().contacts();
      Groups groupsList = app.db().groups();
      ContactData selectedContact = contactsList.iterator().next();
      GroupData selectedGroup = groupsList.iterator().next();


      for (ContactData contact : contactsList) {
        for (GroupData group : groupsList) {
          if (!StringUtils.containsIgnoreCase(contact.getGroups().toString(), group.toString())) {
            selectedContact = contact;
            selectedGroup = group;
            break;
          }
        }
      }
      Groups before= app.db().contactById(selectedContact.getId()).getGroups();
      app.contact().gotoHomePage();
      app.contact().addToGroup(selectedContact, selectedGroup);

      Groups after = app.db().contactById(selectedContact.getId()).getGroups();
      assertThat(after, equalTo(before.withAdded(selectedGroup)));

    }
  }
