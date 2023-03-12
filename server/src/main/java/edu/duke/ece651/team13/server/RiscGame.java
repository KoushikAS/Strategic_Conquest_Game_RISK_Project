package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.Player;
import edu.duke.ece651.team13.shared.map.Map;
import edu.duke.ece651.team13.shared.order.MoveOrder;
import edu.duke.ece651.team13.shared.order.PlayerOrder;

import java.util.ArrayList;

public class RiscGame implements Game{

    private ArrayList<Player> players;
    private int maxPlayers;
    private Board gameBoard;

    public RiscGame(int maxNumPlayers){
        this.maxPlayers = maxNumPlayers;
        this.gameBoard = new RiscGameBoard();
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

    @Override
    public String validateOrders(ArrayList<PlayerOrder> orders) {
        // Deep copy the map
        Map tempMap = gameBoard.getMap().replicate();
        // Move orders first
        for(PlayerOrder order: orders){
           if(order.getClass().equals(MoveOrder.class)){
               String checkResult = order.validateOnMap(tempMap);
               if(checkResult!=null) return checkResult;
               order.actOnMap(tempMap);
           }
        }
        // Attack orders
        // The orders are valid, return null
        return null;
    }

}
