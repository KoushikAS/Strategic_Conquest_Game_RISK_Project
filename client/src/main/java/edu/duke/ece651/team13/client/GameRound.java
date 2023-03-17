package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;

import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.*;

/**
 * This class handles the information of one human player
 */
public class GameRound implements GameRoundInterface {

    private final String playerName;
    private final BoardView boardTextView;
    private final BufferedReader inputReader;
    private final PrintStream out;

    /**
     * Construct a new Game Round
     */
    public GameRound(String playerName, BoardTextView boardTextView, BufferedReader inputReader, PrintStream out) {
        this.playerName = playerName;
        this.boardTextView = boardTextView;
        this.inputReader = inputReader;
        this.out = out;
    }

    /**
     * Returns the orders placed in this round
     */
    @Override
    public ArrayList<PlayerOrderInput> initialRound(MapRO mapRO) throws IOException {
        ArrayList<OrderMappingEnum> validOrdersForRound = new ArrayList<>();
        validOrdersForRound.add(MOVE);

        out.println(boardTextView.displayTerritoriesOfOwner(mapRO, this.playerName));
        out.println("Welcome to RISC Game. You have been assigned player " + playerName + " with the above territories.");
        String prompt = "What would you like to do during Initialisation round?\n" +
                "(M)ove\n" +
                "(D)one";

        return playOneRound(validOrdersForRound, prompt);
    }

    /**
     * Returns the orders placed in this round
     */
    @Override
    public ArrayList<PlayerOrderInput> normalRound(MapRO mapRO) throws IOException {
        ArrayList<OrderMappingEnum> validOrdersForRound = new ArrayList<>();
        validOrdersForRound.add(MOVE);
        validOrdersForRound.add(ATTACK);

        out.println(boardTextView.displayAllTerritories(mapRO));
        out.println("You are the " + playerName + " player");
        String prompt = "what would you like to do?\n" +
                "(M)ove\n" +
                "(A)ttack\n" +
                "(D)one";

        return playOneRound(validOrdersForRound, prompt);
    }

    private ArrayList<PlayerOrderInput> playOneRound(ArrayList<OrderMappingEnum> validOrdersForRound, String prompt) throws IOException {
        ArrayList<PlayerOrderInput> orderInputs = new ArrayList<>();
        while (true) {
            out.println(prompt);
            PlayerOrderInput order = placeOneOrder(validOrdersForRound);
            if (order.getOrderType().equals(DONE)) {
                break;
            }
            orderInputs.add(order);
        }
        return orderInputs;
    }

    /**
     * This helper method chooses an order from the player
     *
     * @return a String of the player's order
     * @throws IOException
     */
    private PlayerOrderInput placeOneOrder(ArrayList<OrderMappingEnum> validOrdersForRound) throws IOException {

        while (true) {
            try {
                OrderMappingEnum orderMappingEnum = readOrder();
                if (orderMappingEnum.equals(DONE)) {
                    return new PlayerOrderInput(orderMappingEnum, "", "", 0);
                }
                if (!validOrdersForRound.contains(orderMappingEnum)) {
                    throw new IllegalArgumentException("Not a valid order for this round");
                }
                String source = readTerritory("Please enter the source Territory");
                String destination = readTerritory("Please enter the destination Territory");
                int unitNumber = readUnitNum();
                return new PlayerOrderInput(orderMappingEnum, source, destination, unitNumber);
            } catch (IllegalArgumentException e) {
                out.println("That is not a valid choice");
                out.println(e.getMessage());
            }
        }
    }

    /**
     * This helper method reads an order from the player's input
     *
     * @return a String of the player's order
     * @throws IOException
     */
    private OrderMappingEnum readOrder() throws IOException {
        String s = inputReader.readLine();
        s= s.toUpperCase();
        return OrderMappingEnum.findByValue(s);
    }

    private String readTerritory(String prompt) throws IOException {
        out.println(prompt);
        //TODO: Basic ErrorCheck for the input
        return inputReader.readLine();
    }


    private int readUnitNum() throws IOException {
        out.println("Please enter unit number");
        String s = inputReader.readLine();
        int unitNum;
        try {
            unitNum = Integer.parseInt(s);
            return unitNum;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Unit Number entered");
        }
    }
}
