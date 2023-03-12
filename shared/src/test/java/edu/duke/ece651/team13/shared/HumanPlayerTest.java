package edu.duke.ece651.team13.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class HumanPlayerTest {
    private final BufferedReader mockedReader = mock(BufferedReader.class);
    private final String testName ="testPlayer";

    @BeforeEach
    void initEach(){
        Player p = new HumanPlayer(testName, mockedReader);
        p.setNextIdToZero();
    }

    @Test
    void test_getId() {
        Player p = new HumanPlayer(testName, mockedReader);
        assertEquals(0, p.getId());
        Player p2 = new HumanPlayer(testName, mockedReader);
        assertEquals(1, p2.getId());
    }

    @Test
    void test_getName() {
        Player p = new HumanPlayer(testName, mockedReader);
        assertEquals(testName, p.getName());
    }

    @Test
    void test_Set_getInitStatus() {
        Player p = new HumanPlayer(testName, mockedReader);
        assertEquals(PlayerStatusEnum.PLAYING, p.getStatus());
        p.setStatus(PlayerStatusEnum.LOSE);
        assertEquals(PlayerStatusEnum.LOSE, p.getStatus());
    }

    @Test
    public void test_playOneTurn() throws IOException {
        Player p = createHumanPlayer("M");
        p.playOneTurn();
        // TODO: record player output using PrintStream
    }

    private Player createHumanPlayer(String inputData) {
        BufferedReader input = new BufferedReader(new StringReader(inputData));
        HumanPlayer p = new HumanPlayer(testName, input);
        return p;
    }
}