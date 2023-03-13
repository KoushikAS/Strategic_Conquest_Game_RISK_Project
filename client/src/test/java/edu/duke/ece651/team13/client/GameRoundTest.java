package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.HumanPlayer;
import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.ATTACK;
import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class GameRoundTest {
    private final BufferedReader mockedReader = mock(BufferedReader.class);
    private final String testName = "testPlayer";

    @BeforeEach
    void initEach() {
        Player p = new HumanPlayer(testName, mockedReader);
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

    @Test
    void test_playOneRound() throws IOException {
        String input = "M\nRottweiler\nPoodle\n10\nM\nRottweiler\nDachshund\n20\nA\nRottweiler\nBoxer\n30\nD\n";
        BufferedReader inputReader = new BufferedReader(new StringReader(input));
        GameRound round = new GameRound("round1", inputReader);
        ArrayList<PlayerOrderInput> orders = round.playOneRound();

        ArrayList<PlayerOrderInput> expected = new ArrayList<>();
        expected.add(new PlayerOrderInput(MOVE, "Rottweiler", "Poodle", 10));
        expected.add(new PlayerOrderInput(MOVE, "Rottweiler", "Dachshund", 20));
        expected.add(new PlayerOrderInput(ATTACK, "Rottweiler", "Boxer", 30));
        for(int i = 0; i < orders.size(); i++){
            assertEquals(expected.get(i), orders.get(i));
        }
    }
}