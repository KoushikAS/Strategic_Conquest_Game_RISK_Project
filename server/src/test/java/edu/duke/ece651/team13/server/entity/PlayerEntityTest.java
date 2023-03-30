package edu.duke.ece651.team13.server.entity;

import static org.junit.jupiter.api.Assertions.*;

import edu.duke.ece651.team13.shared.player.HumanPlayer;
import edu.duke.ece651.team13.shared.player.Player;
import org.junit.jupiter.api.Test;

public class PlayerEntityTest {

  private final String testName = "testPlayer";

  @Test
  void test_getName() {
    PlayerEntity p = new PlayerEntity(testName);
    assertEquals(testName, p.getName());
  }

}
