package edu.duke.ece651.team13.client.round;

import edu.duke.ece651.team13.client.BoardTextView;
import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class SpectateRound extends GameRound {

    /**
     * Construct a Round for spectating.
     */
    public SpectateRound(String playerName, BoardTextView boardTextView, BufferedReader inputReader, PrintStream out) {
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
     * Returns an empty orders list Since the player is only spectating.
     */
    public ArrayList<PlayerOrderInput> executeRound(MapRO mapRO) throws IOException {
        out.println(boardTextView.displayAllTerritories(mapRO));
        out.println("You have Lost the game");

        return new ArrayList<>();
    }

    /**
     * No Valid orders list is set since The player is just spectating.
     **/
    protected ArrayList<OrderMappingEnum> getValidOrder() {
        return new ArrayList<>();
    }


}
