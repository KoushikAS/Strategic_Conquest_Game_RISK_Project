package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.territory.GameTerritory;
import edu.duke.ece651.team13.shared.territory.Territory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static edu.duke.ece651.team13.client.MockDataUtil.getInitalisedV1Map24MapRO;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTextViewTest {
  private MapRO map;
  private BoardTextView view;

  @BeforeEach
  public void setUp() {
    map = getInitalisedV1Map24MapRO();
    view = new BoardTextView();
  }

  @Test
  public void testDisplayOneTerritory() {
    Territory t = new GameTerritory("Narnia");
    String expected = "0 units in Narnia (next to: )\n";
    String actual = view.displayOneTerritory(t);
    assertEquals(expected, actual);
  }

  @Test
  public void testDisplayAllTerritories() throws IOException{
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("BoardDisplayAllTerritories-output.txt");
    String expected = new String(expectedStream.readAllBytes()).replace("\r", "");
    assertEquals(expected, view.displayAllTerritories(map));
  }

  @Test
  public void testDisplayTerritoriesOfOwners() throws IOException{
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("BoardDisplayTerritoriesOfOwner-output.txt");
    String expected = new String(expectedStream.readAllBytes()).replace("\r", "");
    assertEquals(expected, view.displayTerritoriesOfOwner(map,"Red"));
  }
}
