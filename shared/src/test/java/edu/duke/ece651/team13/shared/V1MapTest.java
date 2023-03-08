package edu.duke.ece651.team13.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;

class V1MapTest {
  @Test
  void test_constructor_illegal() {
    assertThrows(IllegalArgumentException.class, () -> new V1Map(0));
    assertThrows(IllegalArgumentException.class, () -> new V1Map(-1));
  }

  @Test
  void test_getInitialUnit() {
    Map m = new V1Map(1);
    assertEquals(1, m.getInitialUnit());
  }

  @Test
  void test_equals() {
    // TODO: Refactor this after refactoring V1Map.equals()
    Map m1 = new V1Map(1);
    Map m2 = new V1Map(2);
    assertNotEquals(m1, m2);
    assertNotEquals(m1, "map");
  }

  @Test
  void test_initMap() {
    Map m1 = new V1Map(5);

    Iterator<Territory> it = m1.getTerritoriesIterator();

    Territory narnia = it.next();
    Territory midkemia = it.next();
    Territory oz = it.next();
    Territory gondor = it.next();
    Territory elantris = it.next();
    Territory scadrial = it.next();
    Territory roshar = it.next();
    Territory hogwarts = it.next();
    Territory mordor = it.next();

    assertEquals(narnia.getName(), "Narnia");
    it = narnia.getNeighbourIterartor();
    assertEquals(midkemia, it.next());
    assertEquals(elantris, it.next());

    assertEquals(midkemia.getName(), "Midkemia");
    it = midkemia.getNeighbourIterartor();
    assertEquals(narnia, it.next());
    assertEquals(oz, it.next());
    assertEquals(scadrial, it.next());

    assertEquals(oz.getName(), "Oz");
    it = oz.getNeighbourIterartor();
    assertEquals(midkemia, it.next());
    assertEquals(gondor, it.next());
    assertEquals(mordor, it.next());

    assertEquals(gondor.getName(), "Gondor");
    it = gondor.getNeighbourIterartor();
    assertEquals(oz, it.next());
    assertEquals(mordor, it.next());

    assertEquals(elantris.getName(), "Elantris");
    it = elantris.getNeighbourIterartor();
    assertEquals(narnia, it.next());
    assertEquals(scadrial, it.next());
    assertEquals(roshar, it.next());

    assertEquals(scadrial.getName(), "Scadrial");
    it = scadrial.getNeighbourIterartor();
    assertEquals(midkemia, it.next());
    assertEquals(elantris, it.next());
    assertEquals(roshar, it.next());
    assertEquals(mordor, it.next());
    assertEquals(hogwarts, it.next());

    assertEquals(roshar.getName(), "Roshar");
    it = roshar.getNeighbourIterartor();
    assertEquals(elantris, it.next());
    assertEquals(scadrial, it.next());
    assertEquals(hogwarts, it.next());
    
    assertEquals(hogwarts.getName(), "Hogwarts");
    it = hogwarts.getNeighbourIterartor();
    assertEquals(scadrial, it.next());
    assertEquals(roshar, it.next());
    assertEquals(mordor, it.next());
    
    assertEquals(mordor.getName(), "Mordor");
    it = mordor.getNeighbourIterartor();
    assertEquals(oz, it.next());
    assertEquals(gondor, it.next());
    assertEquals(scadrial, it.next());
    assertEquals(hogwarts, it.next());
    
  }

}
