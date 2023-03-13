package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.enums.OrderMappingEnum;
import edu.duke.ece651.team13.shared.order.PlayerOrderInput;
import edu.duke.ece651.team13.shared.rulechecker.PlacementChecker;
import edu.duke.ece651.team13.shared.rulechecker.RuleChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static edu.duke.ece651.team13.shared.enums.OrderMappingEnum.DONE;

/**
 * This class handles the information of one human player
 */
public class GameRound implements GameRoundInterface {

    private String name;
    private BufferedReader inputReader;

    /**
     * Construct a new Game Round
     */
    public GameRound(String name, BufferedReader inputReader) {
        this.name = name;
        this.inputReader = inputReader;
    }

    /**
     * Returns the orders placed in this round
     */
    @Override
    public ArrayList<PlayerOrderInput> playOneRound() throws IOException {
        ArrayList<PlayerOrderInput> orderInputs = new ArrayList<>();
        while (true) {
            PlayerOrderInput order = placeOneOrder();
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
    private PlayerOrderInput placeOneOrder() throws IOException {

        while (true) {
            try {
                OrderMappingEnum orderMappingEnum = readOrder();
                if(orderMappingEnum.equals(DONE)){
                    return new PlayerOrderInput(orderMappingEnum, "", "", 0);
                }
                String source = readTerritory("Please enter the source Territory");
                String destination = readTerritory("Please enter the destination Territory");
                int unitNumber = readUnitNum("Please enter unit number");
                return new PlayerOrderInput(orderMappingEnum, source, destination, unitNumber);
            } catch (IllegalArgumentException e) {
                System.out.println("That is not a valid choice");
                System.out.println(e.getMessage());
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
        String prompt = "You are the " + name + " player, what would you like to do?\n" +
                "(M)ove\n" +
                "(A)ttack\n" +
                "(D)one";
        System.out.println(prompt);
        String s = inputReader.readLine().toUpperCase();
        return OrderMappingEnum.findByValue(s);
    }

    /**
     * {@inheritDoc}
     */
    //TODO: this is for the initial phase
    public void placeUnits() throws IOException {
        System.out.println(name + " player, now it's the turn to place your units into your territories.");
        String dest = readTerritory("Which territory do you want to place units into?");
        int unitNum = readUnitNum("How many units do you want to place into that territory");
        RuleChecker checker = new PlacementChecker(null);
        // TODO: need to discuss how to get Board/Map info including Territory and initialUnit here
//        PlacementOrderAdapter order = new PlacementOrderAdapter(checker, this, dest, unitNum, 100);
    }


    private String readTerritory(String prompt) throws IOException {
        System.out.println(prompt);
        String s = inputReader.readLine();
        //TODO: Basic ErrorCheck for the input
        return s;
    }


    private int readUnitNum(String prompt) throws IOException {
        System.out.println(prompt);
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
