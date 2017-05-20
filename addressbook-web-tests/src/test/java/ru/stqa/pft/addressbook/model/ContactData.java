package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String address;
  private final String mobilephone;
  private final String homephone;
  private final String work;
  private final String mail;

  public ContactData(String firstname, String middlename, String lastname, String nickname, String address, String mobilephone, String homephone, String work, String mail) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.address = address;
    this.mobilephone = mobilephone;
    this.homephone = homephone;
    this.work = work;
    this.mail = mail;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getAddress() {
    return address;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getWork() {
    return work;
  }

  public String getMail() {
    return mail;
  }
}
