package edu.duke.ece651.team13.client.round;

import edu.duke.ece651.team13.client.BoardTextView;
import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.MOVE;

public class InitialRound extends GameRound {

    /**
     * Construct a new Game Round
     */
    public InitialRound(String playerName, BoardTextView boardTextView, BufferedReader inputReader, PrintStream out) {
        this.playerName = playerName;
        this.boardTextView = boardTextView;
        this.inputReader = inputReader;
        this.out = out;
        this.prompt = "What would you like to do during Initialisation round?\n" +
                "(M)ove\n" +
                "(D)one";
    }

    /**
     * Returns the orders placed in this round
     */
    public ArrayList<PlayerOrderInput> executeRound(MapRO mapRO) throws IOException {
        out.println(boardTextView.displayTerritoriesOfOwner(mapRO, this.playerName));
        out.println("Welcome to RISC Game. You have been assigned player " + playerName + " with the above territories.");

        return playOneRound();
    }


    protected ArrayList<OrderMappingEnum> getValidOrder(){
        ArrayList<OrderMappingEnum> validOrdersForRound = new ArrayList<>();
        validOrdersForRound.add(MOVE);
        return validOrdersForRound;
    }


}
