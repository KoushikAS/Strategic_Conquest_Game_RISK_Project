package edu.duke.ece651.team13.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiscGameBoardTest {
    @Test
    void test_getMap(){
        Board<Character> b = new RiscGameBoard<>();
        assertEquals(10, b.getMap().getInitialUnit());
    }
}