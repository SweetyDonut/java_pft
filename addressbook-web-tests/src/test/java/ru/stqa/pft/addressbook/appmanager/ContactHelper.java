package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactHelper extends HelperBase {

  private Contacts contactCashe= null;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int Count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    if (contactCashe!=null){
      return contactCashe;
    }
    contactCashe = new Contacts();
    List<WebElement> raws = wd.findElements(By.name("entry"));
    for (WebElement row : raws) {
      List<WebElement> cells= row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String[]phones = cells.get(5).getText().split("\n");
      ContactData contact;
      if (phones.length>=3){
        contact = new ContactData().withId(id).withFirstname(firstName)
                .withLastname(lastName).withHomephone(phones[0]).withMobilephone(phones[1])
                .withWorkphone(phones[2]);
      }
      else {contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName);}

      contactCashe.add(contact);
    }


    return contactCashe;
  }

  public void initCreation() {
    click(By.linkText("add new"));

  }

  public void initDelition() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void initModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id ))).click();
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
    contactCashe=null;

  }

  public void modify(ContactData contact) {
    initModificationById(contact.getId());
    fillForm(contact, false);
    submitModification();
    contactCashe=null;
    returnToHomePage();
  }

  public void create(ContactData contact) {
    initCreation();
    fillForm(contact, true);
    submitCreation();
    contactCashe=null;
    returnToHomePage();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initModificationById(contact.getId());
    String firstname=wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname=wd.findElement(By.name("lastname")).getAttribute("value");
    String home=wd.findElement(By.name("home")).getAttribute("value");
    String mobile=wd.findElement(By.name("mobile")).getAttribute("value");
    String work=wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).
            withLastname(lastname).withHomephone(home).withWorkphone(work).
            withMobilephone(mobile);

  }
}
