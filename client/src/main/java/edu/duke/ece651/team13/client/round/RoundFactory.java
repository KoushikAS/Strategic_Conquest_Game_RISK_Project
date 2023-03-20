package edu.duke.ece651.team13.client.round;

import edu.duke.ece651.team13.client.BoardTextView;
import edu.duke.ece651.team13.client.enums.RoundMapping;

import java.io.BufferedReader;
import java.io.PrintStream;

public class RoundFactory {

    public GameRound getRound(RoundMapping roundMapping, String playerName, BoardTextView boardTextView, BufferedReader inputReader, PrintStream out) {
        switch (roundMapping) {
            case INITIAL_ROUND:
                return new InitialRound(playerName, boardTextView, inputReader, out);
            default:
                return new NormalRound(playerName, boardTextView, inputReader, out);

        }
    }
}
