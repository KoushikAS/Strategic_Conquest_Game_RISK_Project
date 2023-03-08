package edu.duke.ece651.team13.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTextViewTest {
  private V1Map map;
  private BoardTextView view;

  @BeforeEach
  public void setUp() {
    map = new V1Map(10);
    view = new BoardTextView(map);
  }

  @Test
  public void testDisplayOneTerritory() {
    Territory t = new GameTerritory("Narnia");
    String expected = "0 units in Narnia\n";
    String actual = view.displayOneTerritory(t);
    assertEquals(expected, actual);
  }

  @Test
  public void testDisplay() {
    String expected = "Blue player:\n" +
        "-------------\n" +
        "0 units in Narnia\n" +
        "0 units in Gondor\n" +
        "0 units in Roshar\n" +
        "Green player:\n" +
        "-------------\n" +
        "0 units in Midkemia\n" +
        "0 units in Elantris\n" +
        "0 units in Hogwarts\n" +
        "Red player:\n" +
        "-------------\n" +
        "0 units in Oz\n" +
        "0 units in Scadrial\n" +
        "0 units in Mordor\n";
    assertEquals(expected, view.display());
  }
}
