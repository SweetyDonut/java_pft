package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactHelper extends HelperBase{
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));

  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));


  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("middlename"),contactData.getMiddlename());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("nickname"),contactData.getNickname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getHomephone());
    type(By.name("mobile"),contactData.getMobilephone());
    type(By.name("work"),contactData.getWorkPhone());
    type(By.name("email"),contactData.getMail());
    if (creation){
      if (contactData.getGroup()!= null){
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }

    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void initContactCreation() {
    click(By.linkText("add new"));

  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();

  }

  public void initContactDelition() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void submitContactDelition() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.cssSelector("a[href*=\"edit.php?\"]")).get(index).click();
    //String selector = "//table[@id='maintable']/tbody/tr["+index+" ]/td[8]/a/img";

  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact,true);
    submitContactCreation();
    returnToHomePage();
  }

  public boolean isThereAContact() {

      return isElementPresent(By.name("selected[]"));
  }

  public int getGontactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getGontactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element: elements)
         {
           String id = element.findElement(By.tagName("input")).getAttribute("id");
           String lastName = element.findElements(By.tagName("td")).get(1).getText();
           String firstName = element.findElements(By.tagName("td")).get(2).getText();
           ContactData contact = new ContactData(id,firstName,lastName,null,null,null,null,null,null,null,null);
           contacts.add(contact);
    }

    return contacts;
  }
}
