package edu.duke.ece651.team13.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiscGameTest {

    @Test
    void test_getMaxPlayers() {
        RiscGame game = new RiscGame(2);
        assertEquals(2, game.getMaxPlayers());
    }

    @Test
    void test_getBoard() {
        RiscGame game = new RiscGame(1);
        Board expectedBoard = new RiscGameBoard();
        assertEquals(expectedBoard.getMap(), game.getBoard().getMap());
    }
}