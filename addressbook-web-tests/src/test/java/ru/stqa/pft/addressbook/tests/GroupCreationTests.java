package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.goTo().GroupPage();

    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test2");

    app.group().create(group);

    Set<GroupData> after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));

  //group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(group);

    assertThat(after, equalTo(before));
  }

}
