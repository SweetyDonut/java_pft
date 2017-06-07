package ru.stqa.pft.addressbook.model;

public class ContactData {
  private int id;
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String address;
  private final String mobilephone;
  private final String homephone;
  private final String work;
  private final String mail;
  private String group;

  public ContactData(int id, String firstname, String lastname, String middlename, String nickname, String address, String mobilephone, String homephone, String work, String mail, String group) {

    this.id = id;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.address = address;
    this.mobilephone = mobilephone;
    this.homephone = homephone;
    this.work = work;
    this.mail = mail;
    this.group = group;
  }

  public ContactData(String firstname, String middlename, String lastname, String nickname, String address, String mobilephone, String homephone, String work, String mail, String group) {

    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.address = address;
    this.mobilephone = mobilephone;
    this.homephone = homephone;
    this.work = work;
    this.mail = mail;
    this.group = group;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
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

  public String getWorkPhone() {
    return work;
  }

  public String getMail() {
    return mail;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}

