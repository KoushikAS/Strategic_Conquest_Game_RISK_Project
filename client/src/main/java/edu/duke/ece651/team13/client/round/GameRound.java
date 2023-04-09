package edu.duke.ece651.team13.client.round;

import edu.duke.ece651.team13.client.BoardView;
import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;
import edu.duke.ece651.team13.shared.map.MapRO;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.DONE;

/**
 * This class handles the information of one human player
 */
public abstract class GameRound implements GameRoundInterface {

    protected String playerName;
    protected BoardView boardTextView;
    protected BufferedReader inputReader;
    protected PrintStream out;
    protected String prompt;


    public abstract ArrayList<PlayerOrderInput> executeRound(MapRO mapRO) throws IOException;

    abstract ArrayList<OrderMappingEnum> getValidOrder();

    protected ArrayList<PlayerOrderInput> playOneRound() throws IOException {
        ArrayList<PlayerOrderInput> orderInputs = new ArrayList<>();
        while (true) {
            out.println(prompt);
            PlayerOrderInput order = placeOneOrder(this.getValidOrder());
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
        s = s.toUpperCase();
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
