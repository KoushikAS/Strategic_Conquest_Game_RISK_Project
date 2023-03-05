package edu.duke.ece651.team13.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class V1MapTest {
    @Test
    void test_constructor_illegal(){
        assertThrows(IllegalArgumentException.class, ()->new V1Map(0));
        assertThrows(IllegalArgumentException.class, ()->new V1Map(-1));
    }

    @Test
    void test_getInitialUnit() {
        Map m = new V1Map(1);
        assertEquals(1, m.getInitialUnit());
    }

    @Test
    void test_equals(){
        // TODO: Refactor this after refactoring V1Map.equals()
        Map m1 = new V1Map(1);
        Map m2 = new V1Map(2);
        assertNotEquals(m1, m2);
        assertNotEquals(m1, "map");
    }
}