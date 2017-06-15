package ru.stqa.pft.rest;

/**
 * Created by Даниил on 14.06.2017.
 */
public class Issue {
  private int id;
  private String subject;
  private String descripnion;

  public int getId() {
    return id;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public Issue withSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public String getDescripnion() {
    return descripnion;
  }

  public Issue withDescripnion(String descripnion) {
    this.descripnion = descripnion;
    return this;
  }
}
