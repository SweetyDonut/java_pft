package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Даниил on 13.06.2017.
 */

@Entity
@Table(name="mantis_user_table")
public class User {

  @Id
  private int id;


  @Column(name = "username")
  private  String username;

  @Column(name = "email")
  private  String email;

  @Column(name = "password")
  private  String password;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public  String getUsername() {
    return username;
  }


  public User withPassword(String password) {
    this.password = password;
    return this;
  }

  public  String getEmail() {
    return email;
  }

}
