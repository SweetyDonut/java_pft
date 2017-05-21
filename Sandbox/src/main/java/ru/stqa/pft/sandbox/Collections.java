package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Даниил on 21.05.2017.
 */
public class Collections {

  public static void main(String[] args) {
    String[] langs = {"java","c#","Pyton","PHP"};
    List<String> languages = Arrays.asList("java","c#","Pyton","PHP");

    for (String l: languages) {
      System.out.println("Я хочу выучить"+ l);
    }

    for (String l: langs) {
      System.out.println("Я хочу выучить"+ l);
    }

    for (int i = 0; i <languages.size() ; i++) {

      System.out.println("Я хочу выучить"+ languages.get(i));
    }
  }
}
