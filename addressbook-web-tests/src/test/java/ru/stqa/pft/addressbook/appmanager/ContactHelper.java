package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Даниил on 21.05.2017.
 */
public class ContactHelper extends HelperBase{
  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));

  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));


  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("middlename"),contactData.getMiddlename());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("nickname"),contactData.getNickname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getHomephone());
    type(By.name("mobile"),contactData.getMobilephone());
    type(By.name("work"),contactData.getWorkPhone());
    type(By.name("email"),contactData.getMail());

  }

  public void initContactCreation() {
    click(By.linkText("add new"));

  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void initContactDelition() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void submitContactDelition() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }
}
