package edu.duke.ece651.team13.server.entity;

import org.junit.jupiter.api.Test;

import static edu.duke.ece651.team13.server.MockDataUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AttackerEntityTest {

  private final String testName = "testPlayer";

  @Test
  void test_Constructor() {
    TerritoryEntity territory = getTerritoryEntity();
    PlayerEntity player = getPlayerEntity();
    AttackerEntity attacker = new AttackerEntity(territory, player, 5);
    assertEquals(territory, attacker.getTerritory());
    assertEquals(player, attacker.getAttacker());
  }

}
