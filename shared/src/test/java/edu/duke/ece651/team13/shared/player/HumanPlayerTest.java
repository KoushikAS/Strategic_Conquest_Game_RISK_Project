package edu.duke.ece651.team13.shared.player;

import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

class HumanPlayerTest {
    private final BufferedReader mockedReader = mock(BufferedReader.class);
    private final String testName = "testPlayer";

    @BeforeEach
    void initEach() {
        Player p = new HumanPlayer(testName);
    }


    @Test
    void test_getName() {
        Player p = new HumanPlayer(testName);
        assertEquals(testName, p.getName());
    }

    @Test
    void test_Set_getInitStatus() {
        Player p = new HumanPlayer(testName);
        assertEquals(PlayerStatusEnum.PLAYING, p.getStatus());
        p.setStatus(PlayerStatusEnum.LOSE);
        assertEquals(PlayerStatusEnum.LOSE, p.getStatus());
    }

    @Test
    void test_getSocket() {
        Player p = new HumanPlayer(testName);
        assertNull(p.getSocket());
        Socket mockSocket = mock(Socket.class);
        p.setSocket(mockSocket);
        assertEquals(mockSocket, p.getSocket());
    }

}