package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.HumanPlayer;
import edu.duke.ece651.team13.shared.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.mockito.Mockito.mock;

class GameRoundTest {
    private final BufferedReader mockedReader = mock(BufferedReader.class);
    private final String testName = "testPlayer";

    @BeforeEach
    void initEach() {
        Player p = new HumanPlayer(testName);
    }

    @Test
    public void test_playOneTurn() throws IOException {
        GameRound p = createGameRound("M");
        //p.playOneRound();
        // TODO: record player output using PrintStream
    }

    private GameRound createGameRound(String inputData) {
        BufferedReader input = new BufferedReader(new StringReader(inputData));
        return new GameRound(testName, input);
    }
}