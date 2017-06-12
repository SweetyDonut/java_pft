package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name="firstname")
  private String firstname="";

  @Column(name="middlename")
  private String middlename="";

  @Expose
  @Column(name="lastname")
  private String lastname="";

  @Column(name="nickname")
  private String nickname="";

  @Expose
  @Column(name="address")
  @Type(type="text")
  private String address="";

  @Expose
  @Column(name="mobile")
  @Type(type="text")
  private String mobilephone="";

  @Column(name="home")
  @Type(type="text")
  private String homephone="";

  @Column(name="work")
  @Type(type="text")
  private String workphone="";

  @Expose
  @Column(name="email")
  @Type(type="text")
  private String mail="";

  @Column(name="email2")
  @Type(type="text")
  private String mail2="";

  @Column(name="email3")
  @Type(type="text")
  private String mail3="";



  @Transient
  private String group;
  @Transient
  private String allPhones="";

  @Transient
  private String allMails="";

  @Transient
  private String allInfo="";

/*
  @Column(name="photo")
  @Type(type="text")
  private String photo;
*/


  public String getMail2() {
    return mail2;
  }

  public String getMail3() {
    return mail3;
  }

  public ContactData withMail2(String mail2) {
    this.mail2 = mail2;
    return this;
  }

  public ContactData withMail3(String mail3) {
    this.mail3 = mail3;
    return this;
  }
/*  public File getPhoto() {
    if (photo==null){
      return new File("");
    }
    return new File(photo);
  }*/
  /*public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }*/

  public String getAllMails() {
    return allMails;
  }
  public ContactData withAllMails(String allMails) {
    this.allMails = allMails;
    return this;
  }
  public String getAllInfo() {
    return allInfo;
  }
  public ContactData withAllInfo(String allInfo) {
    this.allInfo = allInfo;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public ContactData withHomephone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public ContactData withWorkphone(String work) {
    this.workphone = work;
    return this;
  }

  public ContactData withMail(String mail) {
    this.mail = mail;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
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

  public String getWorkPhone() {
    return workphone;
  }

  public String getMail() {
    return mail;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (mobilephone != null ? !mobilephone.equals(that.mobilephone) : that.mobilephone != null) return false;
    if (homephone != null ? !homephone.equals(that.homephone) : that.homephone != null) return false;
    if (workphone != null ? !workphone.equals(that.workphone) : that.workphone != null) return false;
    if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
    if (mail2 != null ? !mail2.equals(that.mail2) : that.mail2 != null) return false;
    if (mail3 != null ? !mail3.equals(that.mail3) : that.mail3 != null) return false;
    if (allPhones != null ? !allPhones.equals(that.allPhones) : that.allPhones != null) return false;
    if (allMails != null ? !allMails.equals(that.allMails) : that.allMails != null) return false;
    return allInfo != null ? allInfo.equals(that.allInfo) : that.allInfo == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (mobilephone != null ? mobilephone.hashCode() : 0);
    result = 31 * result + (homephone != null ? homephone.hashCode() : 0);
    result = 31 * result + (workphone != null ? workphone.hashCode() : 0);
    result = 31 * result + (mail != null ? mail.hashCode() : 0);
    result = 31 * result + (mail2 != null ? mail2.hashCode() : 0);
    result = 31 * result + (mail3 != null ? mail3.hashCode() : 0);
    result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
    result = 31 * result + (allMails != null ? allMails.hashCode() : 0);
    result = 31 * result + (allInfo != null ? allInfo.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", nickname='" + nickname + '\'' +
            ", address='" + address + '\'' +
            ", mobilephone='" + mobilephone + '\'' +
            ", homephone='" + homephone + '\'' +
            ", workphone='" + workphone + '\'' +
            ", mail='" + mail + '\'' +
            ", mail2='" + mail2 + '\'' +
            ", mail3='" + mail3 + '\'' +
            ", group='" + group + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", allMails='" + allMails + '\'' +
            ", allInfo='" + allInfo + '\'' +
            '}';
  }
}

