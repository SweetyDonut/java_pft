package ru.stqa.pft.sandbox;

/**
 * Created by Даниил on 06.05.2017.
 */
public class PointGame {
  public static void main(String[] args) {
    Point p1 = new Point(0, 1);
    Point p2 = new Point(0, 2);

    System.out.println(distance(p1, p2));
    
    System.out.println(p1.distance(p2));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));

  }
}
