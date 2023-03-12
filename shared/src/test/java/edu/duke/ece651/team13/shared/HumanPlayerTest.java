package edu.duke.ece651.team13.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class HumanPlayerTest {
    private final BufferedReader mockedReader = mock(BufferedReader.class);
    private final String testName = "testPlayer";

    @BeforeEach
    void initEach() {
        Player p = new HumanPlayer(testName, mockedReader);
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
}