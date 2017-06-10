package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGontactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      String lastName = element.findElements(By.tagName("td")).get(1).getText();
      String firstName = element.findElements(By.tagName("td")).get(2).getText();
      ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName);
      contacts.add(contact);
    }

    return contacts;
  }

  public void create(ContactData contact) {
    initCreation();
    fillForm(contact, true);
    submitCreation();
    returnToHomePage();
  }

  public void initCreation() {
    click(By.linkText("add new"));

  }

  public void initDelition() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }


  public void initModificationById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    //String selector = "//table[@id='maintable']/tbody/tr["+index+" ]/td[8]/a/img";
  }


  public void selectById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

  }

  public void submitCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));


  }

  public void submitDelition() {
    wd.switchTo().alert().accept();
  }

  public void submitModification() {
    click(By.name("update"));
  }

  public void fillForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getMail());
    if (creation) {
      if (contactData.getGroup() != null) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }

    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void returnToHomePage() {
    click(By.linkText("home page"));

  }

  public void delite(ContactData contact) {
    selectById(contact.getId());
    initDelition();
    submitDelition();
  }

  public void modify(ContactData contact) {
    initModificationById(contact.getId());
    fillForm(contact, false);
    submitModification();
    returnToHomePage();
  }
}
