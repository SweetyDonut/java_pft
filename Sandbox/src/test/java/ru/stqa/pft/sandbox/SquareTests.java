package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Даниил on 06.05.2017.
 */
public class SquareTests {

  @Test
  public void tesArea(){
    Square s = new Square(5);
    Assert.assertEquals(s.area(),25.0);
  }
}
