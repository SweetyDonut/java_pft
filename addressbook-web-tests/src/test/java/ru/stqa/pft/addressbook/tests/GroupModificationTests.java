package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.*;

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



    List<GroupData> before = app.group().List();

    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withName("test1").withId(modifiedGroup.getId());

    app.group().modify(group);

    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);

  }


}
