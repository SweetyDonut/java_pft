package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by Даниил on 21.05.2017.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size()==0){
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }


  @Test
  public void testGroupModification() {

    Groups before = app.db().groups();

    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withName("tesjdur")
            .withId(modifiedGroup.getId());
    app.goTo().GroupPage();
    app.group().modify(group);

    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.withModified(modifiedGroup,group)));
  }


}
