package edu.duke.ece651.team13.client.round;

import edu.duke.ece651.team13.client.BoardTextView;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static edu.duke.ece651.team13.client.MockDataUtil.getInitalisedV1Map24MapRO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SpectateRoundTest {

    private GameRound createGameRound(String playerName, String inputData, OutputStream bytes) {
        BufferedReader input = new BufferedReader(new StringReader(inputData));
        PrintStream output = new PrintStream(bytes, true);
        return new SpectateRound(playerName, new BoardTextView(), input, output);
    }

    @Test
    void test_playOneRound() throws IOException {
        InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("SpectateRound-output.txt");
        String expectedOutputString = new String(expectedStream.readAllBytes()).replace("\r", "");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        String input = "M\nRottweiler\nPoodle\n10\nM\nRottweiler\nDachshund\n20\nA\nRottweiler\nBoxer\n30\nD\n";
        GameRound round = createGameRound("Red", input, bytes);

        ArrayList<PlayerOrderInput> orders = round.executeRound(getInitalisedV1Map24MapRO());
        assertEquals(0, orders.size());
        assertEquals(expectedOutputString + "\n", bytes.toString());
    }


}