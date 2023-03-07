package edu.duke.ece651.team13.shared;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HumanPlayerTest {
    private final Socket mockedSocket = mock(Socket.class);
    private final String testName ="testPlayer";

    @BeforeEach
    void initEach(){
        Player p = new HumanPlayer(testName, mockedSocket);
        p.setNextIdToZero();
    }

    @Test
    void test_getId() {
        Player p = new HumanPlayer(testName, mockedSocket);
        assertEquals(0, p.getId());
        Player p2 = new HumanPlayer(testName, mockedSocket);
        assertEquals(1, p2.getId());
    }

    @Test
    void test_getName() {
        Player p = new HumanPlayer(testName, mockedSocket);
        assertEquals(testName, p.getName());
    }

    @Test
    void test_Set_getInitStatus() {
        Player p = new HumanPlayer(testName, mockedSocket);
        assertEquals("PLAYING", p.getStatus());
        p.setStatus("LOSE");
        assertEquals("LOSE", p.getStatus());
        assertThrows(IllegalArgumentException.class, () -> p.setStatus("A"));
    }

    @Test
    void test_isValidStatus() {
        Player p = new HumanPlayer(testName, mockedSocket);
        assertTrue(p.isValidStatus("LOSE"));
        assertTrue(p.isValidStatus("PLAYING"));
        assertFalse(p.isValidStatus("1"));
        assertFalse(p.isValidStatus(""));
    }
}