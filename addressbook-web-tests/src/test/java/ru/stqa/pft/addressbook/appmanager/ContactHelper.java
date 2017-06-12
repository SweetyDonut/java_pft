package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactHelper extends HelperBase {

  private Contacts contactCashe = null;

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
    if (contactCashe != null) {
      return contactCashe;
    }
    contactCashe = new Contacts();
    List<WebElement> raws = wd.findElements(By.name("entry"));
    for (WebElement row : raws) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allMails=cells.get(4).getText();
      String allPhones=cells.get(5).getText();
      ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).withAllPhones(allPhones).withAddress(address).withAllMails(allMails);
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
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
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
    type(By.name("email2"), contactData.getMail2());
    type(By.name("email3"), contactData.getMail3());
    /*attach (By.name("photo"),contactData.getPhoto());*/

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size()==1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
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
    contactCashe = null;

  }

  public void modify(ContactData contact) {
    initModificationById(contact.getId());
    fillForm(contact, false);
    submitModification();
    contactCashe = null;
    returnToHomePage();
  }

  public void create(ContactData contact) {
    initCreation();
    fillForm(contact, true);
    submitCreation();
    contactCashe = null;
    //returnToHomePage();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String mail = wd.findElement(By.name("email")).getAttribute("value");
    String mail2 = wd.findElement(By.name("email2")).getAttribute("value");
    String mail3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).
            withLastname(lastname).withHomephone(home).withWorkphone(work).
            withMobilephone(mobile).withMail(mail).withMail2(mail2).withMail3(mail3).withAddress(address);
  }
  public ContactData infoFromViewPage(ContactData contact) {
    goToViewPage(contact.getId());
    String viewInfo = wd.findElement(By.id("content")).getText();
    wd.navigate().back();
    return new ContactData().withAllInfo(viewInfo);
  }

  private void goToViewPage(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
  }

  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }
  public void addToGroup(ContactData contact, GroupData group) {
    selectById(contact.getId());
    SelectedGroup(group);
    contactCashe = null;
    gotoHomePage();
  }
  public void SelectedGroup(GroupData group) {
    new Select(wd.findElement(By.name("to_group"))).selectByValue(""+group.getId());
    click(By.name("add"));
  }
  public void GoToGfilteredPage(){

    wd.findElement(By.cssSelector("[href*='./?group']")).click();
  }
  public void removeFromGroup() {
    click(By.cssSelector("input[name='remove']"));
  }
  public void GoToHPageWithoutGFilter(){

    click(By.xpath("//*[@id=\"header\"]/a"));
  }


  public void deleteFromGroup(ContactData contact, GroupData group) {
    selectById(contact.getId());
    SelectedGroup(group);
    GoToGfilteredPage();
    selectById(contact.getId());
    removeFromGroup();
    contactCashe = null;
    GoToHPageWithoutGFilter();
  }





}
