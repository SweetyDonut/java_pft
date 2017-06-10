package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Даниил on 21.05.2017.
 */
public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int Count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> List() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withName(name).withId(id);
      groups.add(group);
    }
    return groups;
  }

  public void create(GroupData group) {
    initCreation();
    fillForm(group);
    submitCreation();
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectById(group.getId());
    initModification();
    fillForm(group);
    submitModification();
    returnToGroupPage();
  }

  public void select(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectById(int id) {
    wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
  }

  public void deleteSelected() {
    click(By.name("delete"));
  }

  public void initCreation() {
    click(By.name("new"));
  }

  public void initModification() {
    click(By.name("edit"));
  }

  public void fillForm(GroupData groupData) {

    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void submitCreation() {
    click(By.name("submit"));
  }

  public void submitModification() {
    click(By.name("update"));
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void delete(int index) {
    select(index);
    deleteSelected();
    returnToGroupPage();

  }

  public Set<GroupData> all() {
    Set<GroupData> groups = new HashSet<GroupData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withName(name).withId(id);
      groups.add(group);
    }
    return groups;
  }

  public void delete(GroupData group) {
    selectById(group.getId());
    deleteSelected();
    returnToGroupPage();
  }
}
