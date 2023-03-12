package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.territory.GameTerritory;
import edu.duke.ece651.team13.shared.territory.Territory;
import edu.duke.ece651.team13.shared.map.V1Map;
import edu.duke.ece651.team13.shared.map.V1Map9Territories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTextViewTest {
  private V1Map map;
  private BoardTextView view;

  @BeforeEach
  public void setUp() {
    map = new V1Map9Territories(10);
    view = new BoardTextView(map);
  }

  @Test
  public void testDisplayOneTerritory() {
    Territory t = new GameTerritory("Narnia");
    String expected = "0 units in Narnia (next to: )\n";
    String actual = view.displayOneTerritory(t);
    assertEquals(expected, actual);
  }

  @Test
  public void testDisplay() throws IOException{
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("BoardDisplay-output.txt");
    String expected = new String(expectedStream.readAllBytes()).replace("\r", "");
    assertEquals(expected, view.display());
  }
}
