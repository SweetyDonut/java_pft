package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDelitionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().GroupPage();

    if (app.group().all().size()==0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDelition() {
    Set<GroupData> before = app.group().all();

    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);


    List<GroupData> after = app.group().List();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(deletedGroup);
    Assert.assertEquals(before, after);


  }

}
