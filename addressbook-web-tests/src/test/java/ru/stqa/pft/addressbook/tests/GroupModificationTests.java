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

    if (app.Group().List().size()==0) {
      app.Group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {



    List<GroupData> before = app.Group().List();
    int index = before.size() - 1;

    GroupData group = new GroupData().withName("test1").withId(before.get(index).getId());

    app.Group().modify(index, group);

    List<GroupData> after = app.Group().List();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
