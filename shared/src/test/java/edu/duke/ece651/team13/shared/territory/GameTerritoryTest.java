package edu.duke.ece651.team13.shared.territory;

import edu.duke.ece651.team13.shared.AttackerInfo;
import edu.duke.ece651.team13.shared.HumanPlayer;
import edu.duke.ece651.team13.shared.Player;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class GameTerritoryTest {
  private final BufferedReader mockedReader = mock(BufferedReader.class);
  private final String testName = "testTerritory";

  @Test
  void test_getName() {
    Territory t = new GameTerritory(testName);
    assertEquals(testName, t.getName());
  }

  @Test
  void test_get_setUnitNum() {
    Territory t = new GameTerritory(testName);
    assertEquals(0, t.getUnitNum());
    t.setUnitNum(5);
    assertEquals(5, t.getUnitNum());
  }

  @Test
  void test_setUnitNum_illegal() {
    Territory t = new GameTerritory(testName);
    assertThrows(IllegalArgumentException.class, () -> t.setUnitNum(-1));
  }

  @Test
  void test_get_setOwner() {
    Player player = new HumanPlayer("testPlayer", mockedReader);
    Territory t = new GameTerritory(testName);
    assertNull(t.getOwner());
    t.setOwner(player);
    assertEquals(player, t.getOwner());
  }

  @Test
  void test_get_add_clearAttacker() {
    Territory t = new GameTerritory(testName);
    assertFalse(t.getAttackerIterator().hasNext());

    Player attacker1 = new HumanPlayer("attacker1", mockedReader);
    Player attacker2 = new HumanPlayer("attacker2", mockedReader);
    t.addAttacker(attacker1, 10);
    t.addAttacker(attacker2, 8);
    Iterator<AttackerInfo> it = t.getAttackerIterator();
    assertEquals(10, it.next().getUnitNum());
    assertEquals(8, it.next().getUnitNum());

    t.clearAttackers();
    assertFalse(t.getAttackerIterator().hasNext());
  }

  @Test
  void test_get_and_addNeighbour() {
    Territory t = new GameTerritory(testName);
    Territory t2 = new GameTerritory("neighbour1");

    // Check if the neighbours are emepty
    Iterator<TerritoryRO> neighbourIt = t.getNeighbourIterartor();
    assertFalse(neighbourIt.hasNext());

    // Add an element
    t.addNeighbours(t2);
    neighbourIt = t.getNeighbourIterartor();
    assertTrue(neighbourIt.hasNext());
    assertEquals(t2, neighbourIt.next());
    assertFalse(neighbourIt.hasNext());

    // Add Second time an element
    t.addNeighbours(t2);
    neighbourIt = t.getNeighbourIterartor();
    assertTrue(neighbourIt.hasNext());
    assertEquals(t2, neighbourIt.next());
    assertFalse(neighbourIt.hasNext());

    // Adding the same element to the neighbour
    t.addNeighbours(t);
    neighbourIt = t.getNeighbourIterartor();
    assertTrue(neighbourIt.hasNext());
    assertEquals(t2, neighbourIt.next());
    assertFalse(neighbourIt.hasNext());

  }

  @Test
  void test_addAttacker_clearAttackers(){
    Territory t = new GameTerritory(testName);
    Player green = new HumanPlayer("Green", mockedReader);
    t.addAttacker(green ,10);
    Iterator<AttackerInfo> it = t.getAttackerIterator();
    assertTrue(it.hasNext());
    AttackerInfo attackerInfo = it.next();
    assertEquals(10, attackerInfo.getUnitNum());
    assertEquals(green, attackerInfo.getAttacker());

    t.clearAttackers();
    assertFalse(t.getAttackerIterator().hasNext());
  }

}
