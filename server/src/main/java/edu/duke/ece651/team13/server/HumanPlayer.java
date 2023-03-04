package edu.duke.ece651.team13.server;

/**
 * This class handles the information of one human player
 */
public class HumanPlayer implements Player{
    int id;
    String name;
    //TODO: Add client and status

    public HumanPlayer(int id,
                       String name){
        this.id = id;
        this.name = name;
    }
}
