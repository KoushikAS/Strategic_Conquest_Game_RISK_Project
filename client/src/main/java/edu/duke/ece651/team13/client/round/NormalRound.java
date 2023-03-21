package edu.duke.ece651.team13.client.round;

import edu.duke.ece651.team13.client.BoardTextView;
import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.ATTACK;
import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;

public class NormalRound extends GameRound {

    /**
     * Construct a new Game Round
     */
    public NormalRound(String playerName, BoardTextView boardTextView, BufferedReader inputReader, PrintStream out) {
        this.playerName = playerName;
        this.boardTextView = boardTextView;
        this.inputReader = inputReader;
        this.out = out;
        prompt = "what would you like to do?\n" +
                "(M)ove\n" +
                "(A)ttack\n" +
                "(D)one";
    }

    /**
     * Returns the orders placed in this round
     */
    public ArrayList<PlayerOrderInput> executeRound(MapRO mapRO) throws IOException {
        out.println(boardTextView.displayAllTerritories(mapRO));
        out.println("You are the " + playerName + " player");

        return playOneRound();
    }


    protected ArrayList<OrderMappingEnum> getValidOrder(){
        ArrayList<OrderMappingEnum> validOrdersForRound = new ArrayList<>();
        validOrdersForRound.add(MOVE);
        validOrdersForRound.add(ATTACK);
        return validOrdersForRound;
    }


}
