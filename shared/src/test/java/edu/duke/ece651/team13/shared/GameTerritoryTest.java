package edu.duke.ece651.team13.shared;

import org.junit.jupiter.api.BeforeEach;
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

  @BeforeEach
  void initEach() {
    Territory t = new GameTerritory(testName);
    t.setNextIdToZero();
  }

  @Test
  void test_getId() {
    Territory t = new GameTerritory(testName);
    assertEquals(0, t.getId());
    Territory t2 = new GameTerritory(testName);
    assertEquals(1, t2.getId());
  }

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
  void test_get_setTempUnitNum() {
    Territory t = new GameTerritory(testName);
    assertEquals(0, t.getTempUnitNum());
    t.setTempUnitNum(5);
    assertEquals(5, t.getTempUnitNum());
    assertEquals(0, t.getUnitNum());
  }

  @Test
  void test_setTempUnitNum_illegal() {
    Territory t = new GameTerritory(testName);
    assertThrows(IllegalArgumentException.class, () -> t.setTempUnitNum(-1));
  }

  @Test
  void test_rollback_commitTempUnitNum() {
    Territory t = new GameTerritory(testName);
    t.setTempUnitNum(5);
    assertEquals(5, t.getTempUnitNum());
    assertEquals(0, t.getUnitNum());
    t.commitTempUnitNum();
    assertEquals(5, t.getUnitNum());
    t.setTempUnitNum(10);
    assertEquals(10, t.getTempUnitNum());
    t.rollbackTempUnitNum();
    assertEquals(5, t.getTempUnitNum());
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
    assertTrue(t.getAttackers().isEmpty());

    Player attacker1 = new HumanPlayer("attacker1", mockedReader);
    Player attacker2 = new HumanPlayer("attacker2", mockedReader);
    t.addAttacker(attacker1, 10);
    t.addAttacker(attacker2, 8);
    assertEquals(10, t.getAttackers().get(attacker1));
    assertEquals(8, t.getAttackers().get(attacker2));

    t.clearAttackers();
    assertTrue(t.getAttackers().isEmpty());
  }

  @Test
  void test_get_and_addNeighbour() {
    Territory t = new GameTerritory(testName);
    Territory t2 = new GameTerritory("neighbour1");

    // Check if the neighbours are emepty
    Iterator<Territory> neighbourIt = t.getNeighbourIterartor();
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

}
