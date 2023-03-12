package edu.duke.ece651.team13.client;

import edu.duke.ece651.team13.shared.rulechecker.PlacementChecker;
import edu.duke.ece651.team13.shared.rulechecker.RuleChecker;

import java.io.BufferedReader;
import java.io.IOException;

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
    public void playOneRound() throws IOException {
        String order = chooseOrder();
        if (order.equals("M")) {
            System.out.println(name + " is performing a Move order...");
        } else if (order.equals("A")) {
            System.out.println(name + " is performing an Attack order...");
        } else {
            System.out.println(name + " is done with this turn.");
        }
    }

    /**
     * This helper method chooses an order from the player
     *
     * @return a String of the player's order
     * @throws IOException
     */
    private String chooseOrder() throws IOException {
        String prompt = "You are the " + name + " player, what would you like to do?\n" +
                "(M)ove\n" +
                "(A)ttack\n" +
                "(D)one";
        String order;
        while (true) {
            order = readOrder(prompt);
            if (order != null) break;
        }
        return order;
    }

    /**
     * This helper method reads an order from the player's input
     *
     * @param prompt the prompt message to guide the player's input
     * @return a String of the player's order
     * @throws IOException
     */
    private String readOrder(String prompt) throws IOException {
        System.out.println(prompt);
        String s = inputReader.readLine().toUpperCase();
        if (s.equals("M") || s.equals("A") || s.equals("D")) {
            return s;
        } else {
            System.out.println("That is not a valid choice");
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    //TODO: this is for the initial phase
    public void placeUnits() throws IOException {
        System.out.println(name + " player, now it's the turn to place your units into your territories.");
        String dest = chooseDestination("Which territory do you want to place units into?");
        int unitNum = chooseUnitNum("How many units do you want to place into that territory");
        RuleChecker checker = new PlacementChecker(null);
        // TODO: need to discuss how to get Board/Map info including Territory and initialUnit here
//        PlacementOrderAdapter order = new PlacementOrderAdapter(checker, this, dest, unitNum, 100);
    }

    private String chooseDestination(String prompt) throws IOException {
        String dest;
        while (true) {
            dest = readDestination(prompt);
            if (dest != null) break;
        }
        return dest;
    }

    private String readDestination(String prompt) throws IOException {
        System.out.println(prompt);
        String s = inputReader.readLine();
        return s;
    }

    private int chooseUnitNum(String prompt) throws IOException {
        int unitNum;
        while (true) {
            unitNum = readUnitNum(prompt);
            if (unitNum != -1) break;
        }
        return unitNum;
    }

    private int readUnitNum(String prompt) throws IOException {
        System.out.println(prompt);
        String s = inputReader.readLine();
        int unitNum;
        try {
            unitNum = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
        return unitNum;
    }


}
