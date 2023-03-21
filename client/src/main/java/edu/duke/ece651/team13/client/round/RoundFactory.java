package edu.duke.ece651.team13.client.round;

import edu.duke.ece651.team13.client.BoardTextView;
import edu.duke.ece651.team13.client.enums.RoundMapping;

import java.io.BufferedReader;
import java.io.PrintStream;

public class RoundFactory {
    private final String playerName;
    private final BoardTextView boardTextView;
    private final BufferedReader inputReader;
    private final PrintStream out;

    public RoundFactory(String playerName, BoardTextView boardTextView, BufferedReader inputReader, PrintStream out){
        this.playerName = playerName;
        this.boardTextView = boardTextView;
        this.inputReader = inputReader;
        this.out = out;
    }

    public GameRound getRound(RoundMapping roundMapping) {
        switch (roundMapping) {
            case INITIAL_ROUND:
                return new InitialRound(playerName, boardTextView, inputReader, out);
            default:
                return new NormalRound(playerName, boardTextView, inputReader, out);

        }
    }
}
