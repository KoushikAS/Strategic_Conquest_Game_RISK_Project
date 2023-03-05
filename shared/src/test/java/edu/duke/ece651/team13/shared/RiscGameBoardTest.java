package edu.duke.ece651.team13.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RiscGameBoardTest {
    @Test
    void test_getMap(){
        Board<Character> b = new RiscGameBoard<>();
        assertEquals(10, b.getMap().getInitialUnit());
    }
}