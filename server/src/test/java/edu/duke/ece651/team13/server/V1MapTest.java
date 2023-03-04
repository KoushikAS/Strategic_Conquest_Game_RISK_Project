package edu.duke.ece651.team13.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}