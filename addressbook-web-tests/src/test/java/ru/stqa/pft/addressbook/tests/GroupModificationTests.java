package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by Даниил on 21.05.2017.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();

    if (app.group().all().size()==0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {

    Groups before = app.group().all();

    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withName("tesjdur").withId(modifiedGroup.getId());

    app.group().modify(group);

    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.withModified(modifiedGroup,group)));
  }


}
