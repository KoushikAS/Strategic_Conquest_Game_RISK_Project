package edu.duke.ece651.team13.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiscGameBoardTest {
    @Test
    void test_getMap(){
        Board<Character> board2player = new RiscGameBoard<>(2);
        assertEquals(12, board2player.getMap().getInitialUnit());

        Board<Character> board3player = new RiscGameBoard<>(3);
        assertEquals(18, board3player.getMap().getInitialUnit());

        Board<Character> board4player = new RiscGameBoard<>(4);
        assertEquals(24, board4player.getMap().getInitialUnit());
    }
}