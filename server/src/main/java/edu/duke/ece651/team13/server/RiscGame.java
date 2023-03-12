package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.Player;

import java.util.ArrayList;

public class RiscGame implements Game{

    private ArrayList<Player> players;
    private int maxPlayers;
    private Board gameBoard;

    public RiscGame(int maxNumPlayers){
        this.maxPlayers = maxNumPlayers;
        this.gameBoard = new RiscGameBoard(maxNumPlayers);
        this.players = new ArrayList<>();
    }

    @Override
    public void initGame(){}

    @Override
    public void initPlayer(){}

    /**
     * Get the number of players
     * @return int number
     */
    @Override
    public int getMaxPlayers(){ return this.maxPlayers;}

    /**
     * Get the board of one game
     * @return Board game board
     */
    @Override
    public Board getBoard(){ return this.gameBoard;}

    @Override
    public void playOneTurn(){}

}
